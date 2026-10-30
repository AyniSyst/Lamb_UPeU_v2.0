package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.SolicitudAperturaNotasRequest;
import pe.edu.upeu.lamb.evaluacion.dto.SolicitudAperturaNotasResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.evaluacion.entity.SolicitudAperturaNotas;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.SolicitudAperturaNotasMapper;
import pe.edu.upeu.lamb.evaluacion.repository.SolicitudAperturaNotasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudAperturaNotasService {

    private final SolicitudAperturaNotasRepository solicitudAperturaNotasRepository;
    private final SolicitudAperturaNotasMapper solicitudAperturaNotasMapper;

    @Transactional(readOnly = true)
    public List<SolicitudAperturaNotasResponse> listar(Long idGrupo) {
        List<SolicitudAperturaNotas> lista = idGrupo == null
                ? solicitudAperturaNotasRepository.findAll()
                : solicitudAperturaNotasRepository.findByIdGrupo(idGrupo);
        return lista.stream().map(solicitudAperturaNotasMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudAperturaNotasResponse obtener(Long id) {
        return solicitudAperturaNotasMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudAperturaNotasResponse crear(SolicitudAperturaNotasRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudAperturaNotas solicitudAperturaNotas = solicitudAperturaNotasMapper.toEntity(request);
        solicitudAperturaNotas.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudAperturaNotas.setIdSolicitante(idSolicitante);
        solicitudAperturaNotas.setFechaSolicitud(LocalDateTime.now());
        return solicitudAperturaNotasMapper.toResponse(solicitudAperturaNotasRepository.save(solicitudAperturaNotas));
    }

    @Transactional
    public SolicitudAperturaNotasResponse actualizar(Long id, SolicitudAperturaNotasRequest request) {
        SolicitudAperturaNotas solicitudAperturaNotas = buscarOFallar(id);
        validarPendiente(solicitudAperturaNotas);
        validar(request, id);
        solicitudAperturaNotas.setIdGrupo(request.getIdGrupo());
        solicitudAperturaNotas.setMotivo(request.getMotivo().trim());
        solicitudAperturaNotas.setFechaInicioPropuesta(request.getFechaInicioPropuesta());
        solicitudAperturaNotas.setFechaFinPropuesta(request.getFechaFinPropuesta());
        return solicitudAperturaNotasMapper.toResponse(solicitudAperturaNotasRepository.save(solicitudAperturaNotas));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudAperturaNotas solicitudAperturaNotas = buscarOFallar(id);
        validarPendiente(solicitudAperturaNotas);
        solicitudAperturaNotasRepository.delete(solicitudAperturaNotas);
    }

    SolicitudAperturaNotas buscarOFallar(Long id) {
        return solicitudAperturaNotasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de apertura para notas rezagadas no encontrada: " + id));
    }

    private void validar(SolicitudAperturaNotasRequest request, Long id) {
        if (request.getFechaFinPropuesta().isBefore(request.getFechaInicioPropuesta())) {
            throw new BusinessRuleException("Fecha fin propuesta no puede ser anterior a fecha inicio propuesta");
        }
    }

    void validarPendiente(SolicitudAperturaNotas solicitudAperturaNotas) {
        if (solicitudAperturaNotas.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudAperturaNotas.getId() + " esta " + solicitudAperturaNotas.getEstado() + " y ya no se puede modificar");
        }
    }
}
