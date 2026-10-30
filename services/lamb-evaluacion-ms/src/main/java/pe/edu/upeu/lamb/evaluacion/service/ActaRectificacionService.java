package pe.edu.upeu.lamb.evaluacion.service;

import pe.edu.upeu.lamb.evaluacion.dto.ActaRectificacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ActaRectificacionResponse;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.evaluacion.entity.Acta;
import pe.edu.upeu.lamb.evaluacion.entity.ActaRectificacion;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.evaluacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.evaluacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.evaluacion.mapper.ActaRectificacionMapper;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRectificacionRepository;
import pe.edu.upeu.lamb.evaluacion.repository.ActaRepository;
import pe.edu.upeu.lamb.evaluacion.entity.EstadoActa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActaRectificacionService {

    private final ActaRectificacionRepository actaRectificacionRepository;
    private final ActaRepository actaRepository;
    private final ActaRectificacionMapper actaRectificacionMapper;

    @Transactional(readOnly = true)
    public List<ActaRectificacionResponse> listar(Long idActa) {
        List<ActaRectificacion> lista = idActa == null
                ? actaRectificacionRepository.findAll()
                : actaRectificacionRepository.findByActaId(idActa);
        return lista.stream().map(actaRectificacionMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ActaRectificacionResponse obtener(Long id) {
        return actaRectificacionMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public ActaRectificacionResponse crear(ActaRectificacionRequest request, Long idSolicitante) {
        validar(request, null);
        ActaRectificacion actaRectificacion = actaRectificacionMapper.toEntity(request);
        asignarRelaciones(actaRectificacion, request);
        actaRectificacion.setEstado(EstadoSolicitud.PENDIENTE);
        actaRectificacion.setIdSolicitante(idSolicitante);
        actaRectificacion.setFechaSolicitud(LocalDateTime.now());
        return actaRectificacionMapper.toResponse(actaRectificacionRepository.save(actaRectificacion));
    }

    @Transactional
    public ActaRectificacionResponse actualizar(Long id, ActaRectificacionRequest request) {
        ActaRectificacion actaRectificacion = buscarOFallar(id);
        validarPendiente(actaRectificacion);
        validar(request, id);
        actaRectificacion.setIdPersona(request.getIdPersona());
        actaRectificacion.setNotaAnterior(request.getNotaAnterior());
        actaRectificacion.setNotaNueva(request.getNotaNueva());
        actaRectificacion.setMotivo(request.getMotivo().trim());
        actaRectificacion.setResolucion(request.getResolucion());
        asignarRelaciones(actaRectificacion, request);
        return actaRectificacionMapper.toResponse(actaRectificacionRepository.save(actaRectificacion));
    }

    @Transactional
    public void eliminar(Long id) {
        ActaRectificacion actaRectificacion = buscarOFallar(id);
        validarPendiente(actaRectificacion);
        actaRectificacionRepository.delete(actaRectificacion);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public ActaRectificacionResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        ActaRectificacion actaRectificacion = buscarOFallar(id);
        validarPendiente(actaRectificacion);
        actaRectificacion.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        actaRectificacion.setObservacionRespuesta(request.getObservacion());
        actaRectificacion.setIdRespondidoPor(idResponsable);
        actaRectificacion.setFechaRespuesta(LocalDateTime.now());
        return actaRectificacionMapper.toResponse(actaRectificacionRepository.save(actaRectificacion));
    }

    ActaRectificacion buscarOFallar(Long id) {
        return actaRectificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acta de rectificacion no encontrada: " + id));
    }

    private void validar(ActaRectificacionRequest request, Long id) {
    }

    private void asignarRelaciones(ActaRectificacion actaRectificacion, ActaRectificacionRequest request) {
        actaRectificacion.setActa(actaRepository.findById(request.getIdActa())
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + request.getIdActa())));
        Acta acta = actaRepository.findById(request.getIdActa())
                .orElseThrow(() -> new ResourceNotFoundException("Acta no encontrada: " + request.getIdActa()));
        if (acta.getEstado() != EstadoActa.CERRADA) {
            throw new BusinessRuleException("El acta " + acta.getId() + " esta " + acta.getEstado() + "; esta operacion es sobre un acta CERRADA");
        }
    }

    private void validarPendiente(ActaRectificacion actaRectificacion) {
        if (actaRectificacion.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + actaRectificacion.getId() + " esta " + actaRectificacion.getEstado() + " y ya no se puede modificar");
        }
    }
}
