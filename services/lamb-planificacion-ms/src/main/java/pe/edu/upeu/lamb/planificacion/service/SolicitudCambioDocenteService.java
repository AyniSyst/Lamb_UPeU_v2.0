package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.AsignacionDocente;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioDocente;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SolicitudCambioDocenteMapper;
import pe.edu.upeu.lamb.planificacion.repository.AsignacionDocenteRepository;
import pe.edu.upeu.lamb.planificacion.repository.SolicitudCambioDocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudCambioDocenteService {

    private final SolicitudCambioDocenteRepository solicitudCambioDocenteRepository;
    private final AsignacionDocenteRepository asignacionDocenteRepository;
    private final SolicitudCambioDocenteMapper solicitudCambioDocenteMapper;
    private final AsignacionDocenteService asignacionDocenteService;

    @Transactional(readOnly = true)
    public List<SolicitudCambioDocenteResponse> listar(Long idAsignacion) {
        List<SolicitudCambioDocente> lista = idAsignacion == null
                ? solicitudCambioDocenteRepository.findAll()
                : solicitudCambioDocenteRepository.findByAsignacionId(idAsignacion);
        return lista.stream().map(solicitudCambioDocenteMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudCambioDocenteResponse obtener(Long id) {
        return solicitudCambioDocenteMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudCambioDocenteResponse crear(SolicitudCambioDocenteRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudCambioDocente solicitudCambioDocente = solicitudCambioDocenteMapper.toEntity(request);
        asignarRelaciones(solicitudCambioDocente, request);
        solicitudCambioDocente.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudCambioDocente.setIdSolicitante(idSolicitante);
        solicitudCambioDocente.setFechaSolicitud(LocalDateTime.now());
        return solicitudCambioDocenteMapper.toResponse(solicitudCambioDocenteRepository.save(solicitudCambioDocente));
    }

    @Transactional
    public SolicitudCambioDocenteResponse actualizar(Long id, SolicitudCambioDocenteRequest request) {
        SolicitudCambioDocente solicitudCambioDocente = buscarOFallar(id);
        validarPendiente(solicitudCambioDocente);
        validar(request, id);
        solicitudCambioDocente.setIdDocenteNuevo(request.getIdDocenteNuevo());
        solicitudCambioDocente.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudCambioDocente, request);
        return solicitudCambioDocenteMapper.toResponse(solicitudCambioDocenteRepository.save(solicitudCambioDocente));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudCambioDocente solicitudCambioDocente = buscarOFallar(id);
        validarPendiente(solicitudCambioDocente);
        solicitudCambioDocenteRepository.delete(solicitudCambioDocente);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudCambioDocenteResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudCambioDocente solicitudCambioDocente = buscarOFallar(id);
        validarPendiente(solicitudCambioDocente);
        if (Boolean.TRUE.equals(request.getAprobada())) {
            aplicarAprobacion(solicitudCambioDocente);
        }
        solicitudCambioDocente.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudCambioDocente.setObservacionRespuesta(request.getObservacion());
        solicitudCambioDocente.setIdRespondidoPor(idResponsable);
        solicitudCambioDocente.setFechaRespuesta(LocalDateTime.now());
        return solicitudCambioDocenteMapper.toResponse(solicitudCambioDocenteRepository.save(solicitudCambioDocente));
    }

    SolicitudCambioDocente buscarOFallar(Long id) {
        return solicitudCambioDocenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de cambio de docente no encontrada: " + id));
    }

    private void validar(SolicitudCambioDocenteRequest request, Long id) {
    }

    private void asignarRelaciones(SolicitudCambioDocente solicitudCambioDocente, SolicitudCambioDocenteRequest request) {
        solicitudCambioDocente.setAsignacion(asignacionDocenteRepository.findById(request.getIdAsignacion())
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion docente no encontrada: " + request.getIdAsignacion())));
    }

    private void validarPendiente(SolicitudCambioDocente solicitudCambioDocente) {
        if (solicitudCambioDocente.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudCambioDocente.getId() + " esta " + solicitudCambioDocente.getEstado() + " y ya no se puede modificar");
        }
    }

    private void aplicarAprobacion(SolicitudCambioDocente solicitudCambioDocente) {
        // Misma validacion de cruces, carga lectiva y candado por docente que la asignacion normal
        AsignacionDocente asignacion = solicitudCambioDocente.getAsignacion();
        asignacionDocenteService.reasignar(asignacion, solicitudCambioDocente.getIdDocenteNuevo(),
                asignacion.getRol(), asignacion.getHorasSemanales());
    }
}
