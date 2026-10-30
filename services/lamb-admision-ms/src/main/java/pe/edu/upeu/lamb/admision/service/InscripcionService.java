package pe.edu.upeu.lamb.admision.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.admision.dto.DocumentoPresentadoRequest;
import pe.edu.upeu.lamb.admision.dto.InscripcionRequest;
import pe.edu.upeu.lamb.admision.dto.InscripcionResponse;
import pe.edu.upeu.lamb.admision.dto.ResponderInscripcionRequest;
import pe.edu.upeu.lamb.admision.entity.*;
import pe.edu.upeu.lamb.admision.exception.BusinessRuleException;
import pe.edu.upeu.lamb.admision.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.admision.mapper.InscripcionMapper;
import pe.edu.upeu.lamb.admision.repository.InscripcionRepository;
import pe.edu.upeu.lamb.admision.repository.ModalidadAdmisionRepository;
import pe.edu.upeu.lamb.admision.repository.TipoDocumentoRepository;

import java.time.Instant;
import java.util.List;

/**
 * Servicio transaccional de admision: cabecera (Inscripcion) + detalle
 * (DocumentoPresentado) + regla de negocio real (documentos obligatorios de
 * la modalidad) + reserva de vacante bajo bloqueo pesimista.
 *
 * Toda la operacion vive en una sola transaccion: si alguna regla falla,
 * no se guarda cabecera ni detalles, ni se mueve el contador de vacantes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final ModalidadAdmisionRepository modalidadRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final ReglasInscripcionService reglas;
    private final InscripcionMapper mapper;

    @Transactional(readOnly = true)
    public List<InscripcionResponse> listar(Long idPersona, String periodo) {
        List<Inscripcion> data;
        if (idPersona != null) {
            data = inscripcionRepository.findByIdPersona(idPersona);
        } else if (periodo != null) {
            data = inscripcionRepository.findByPeriodo(periodo);
        } else {
            data = inscripcionRepository.findAll();
        }
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public InscripcionResponse obtener(Long id) {
        return mapper.toResponse(buscarOFallar(id));
    }

    /**
     * Operacion transaccional principal: crea la cabecera + todos los documentos
     * presentados en una sola transaccion.
     *
     * Reglas que corren aqui:
     * 1. No duplicar postulacion (misma persona + programa + periodo).
     * 2. La modalidad existe y esta activa.
     * 3. Los tipos de documento presentados son los que la modalidad acepta.
     * 4. No se repite el mismo tipo de documento dos veces en el request.
     *
     * La inscripcion queda en PENDIENTE. La aprobacion (que exige documentos
     * obligatorios completos + reserva de vacante) se hace en aprobar(...).
     */
    @Transactional
    public InscripcionResponse crear(InscripcionRequest request) {
        if (inscripcionRepository.existsByIdPersonaAndIdProgramaAndPeriodo(
                request.getIdPersona(), request.getIdPrograma(), request.getPeriodo())) {
            throw new BusinessRuleException("Ya existe una inscripcion de la persona "
                    + request.getIdPersona() + " al programa " + request.getIdPrograma()
                    + " en el periodo " + request.getPeriodo());
        }

        ModalidadAdmision modalidad = modalidadRepository.findById(request.getIdModalidad())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Modalidad no encontrada: " + request.getIdModalidad()));
        if (Boolean.FALSE.equals(modalidad.getActivo())) {
            throw new BusinessRuleException("La modalidad " + modalidad.getCodigo() + " no esta activa");
        }

        validarTiposDocumentoUnicos(request.getDocumentos());

        Inscripcion inscripcion = Inscripcion.builder()
                .idPersona(request.getIdPersona())
                .idPrograma(request.getIdPrograma())
                .modalidad(modalidad)
                .periodo(request.getPeriodo())
                .estado(EstadoInscripcion.PENDIENTE)
                .observacion(request.getObservacion())
                .fechaRegistro(Instant.now())
                .build();

        for (DocumentoPresentadoRequest d : request.getDocumentos()) {
            TipoDocumento tipo = tipoDocumentoRepository.findById(d.getIdTipoDocumento())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Tipo de documento no encontrado: " + d.getIdTipoDocumento()));
            DocumentoPresentado doc = DocumentoPresentado.builder()
                    .tipoDocumento(tipo)
                    .numeroDocumento(d.getNumeroDocumento())
                    .urlArchivo(d.getUrlArchivo())
                    .observacion(d.getObservacion())
                    .fechaEntrega(Instant.now())
                    .build();
            inscripcion.agregarDocumento(doc);
        }

        reglas.validarTiposDocumentoPermitidos(modalidad, inscripcion.getDocumentos());

        Inscripcion guardada = inscripcionRepository.save(inscripcion);
        log.info("component=inscripcion id={} idPersona={} modalidad={} documentos={} status=creada",
                guardada.getId(), guardada.getIdPersona(), modalidad.getCodigo(),
                guardada.getDocumentos().size());
        return mapper.toResponse(guardada);
    }

    /**
     * Responde una inscripcion PENDIENTE. Aprobar exige que esten TODOS los
     * documentos obligatorios de la modalidad + reserva una vacante bajo
     * bloqueo pesimista. Si cualquier regla falla, se hace rollback: ni se
     * cambia estado ni se mueve el contador de vacantes.
     */
    @Transactional
    public InscripcionResponse responder(Long id, ResponderInscripcionRequest request, Long idRespondidoPor) {
        Inscripcion inscripcion = buscarOFallar(id);
        if (inscripcion.getEstado() != EstadoInscripcion.PENDIENTE) {
            throw new BusinessRuleException("Solo se puede responder una inscripcion PENDIENTE (actual: "
                    + inscripcion.getEstado() + ")");
        }

        if (Boolean.TRUE.equals(request.getAprobada())) {
            // Bloqueo pesimista sobre la fila de la modalidad: dos aprobaciones
            // simultaneas se serializan. La 2da ve el contador actualizado y, si
            // no hay cupo, rebota con 409 antes del commit.
            ModalidadAdmision modalidadBloqueada = modalidadRepository
                    .findByIdParaReserva(inscripcion.getModalidad().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Modalidad no encontrada: " + inscripcion.getModalidad().getId()));

            reglas.validarDocumentosRequeridos(modalidadBloqueada, inscripcion.getDocumentos());
            reglas.reservarVacante(modalidadBloqueada);

            inscripcion.setEstado(EstadoInscripcion.APROBADA);
            inscripcion.setFechaAprobacion(Instant.now());
            inscripcion.setIdAprobadoPor(idRespondidoPor);
            inscripcion.setObservacion(request.getObservacion());
            log.info("component=inscripcion id={} status=aprobada modalidad={} vacantes={}/{}",
                    inscripcion.getId(), modalidadBloqueada.getCodigo(),
                    modalidadBloqueada.getVacantesOcupadas(),
                    modalidadBloqueada.getVacantesDisponibles());
        } else {
            inscripcion.setEstado(EstadoInscripcion.RECHAZADA);
            inscripcion.setFechaAprobacion(Instant.now());
            inscripcion.setIdAprobadoPor(idRespondidoPor);
            inscripcion.setObservacion(request.getObservacion());
            log.info("component=inscripcion id={} status=rechazada", inscripcion.getId());
        }
        return mapper.toResponse(inscripcion);
    }

    /**
     * Anula una inscripcion ya aprobada: libera la vacante dentro de la misma
     * transaccion (compensacion). Si la aprobacion y la anulacion ocurren en
     * paralelo, el bloqueo pesimista sobre la modalidad las serializa.
     */
    @Transactional
    public InscripcionResponse anular(Long id, String motivo) {
        Inscripcion inscripcion = buscarOFallar(id);
        if (inscripcion.getEstado() == EstadoInscripcion.ANULADA) {
            return mapper.toResponse(inscripcion);
        }
        if (inscripcion.getEstado() == EstadoInscripcion.APROBADA) {
            ModalidadAdmision modalidadBloqueada = modalidadRepository
                    .findByIdParaReserva(inscripcion.getModalidad().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Modalidad no encontrada: " + inscripcion.getModalidad().getId()));
            reglas.liberarVacante(modalidadBloqueada);
        }
        inscripcion.setEstado(EstadoInscripcion.ANULADA);
        inscripcion.setObservacion(motivo);
        return mapper.toResponse(inscripcion);
    }

    private void validarTiposDocumentoUnicos(List<DocumentoPresentadoRequest> documentos) {
        long distintos = documentos.stream().map(DocumentoPresentadoRequest::getIdTipoDocumento).distinct().count();
        if (distintos != documentos.size()) {
            throw new BusinessRuleException("No se puede presentar el mismo tipo de documento dos veces");
        }
    }

    private Inscripcion buscarOFallar(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripcion no encontrada: " + id));
    }
}
