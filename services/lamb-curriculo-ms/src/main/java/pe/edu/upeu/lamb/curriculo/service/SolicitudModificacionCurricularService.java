package pe.edu.upeu.lamb.curriculo.service;

import pe.edu.upeu.lamb.curriculo.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.curriculo.dto.SolicitudModificacionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.SolicitudModificacionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.entity.SolicitudModificacionCurricular;
import pe.edu.upeu.lamb.curriculo.exception.BusinessRuleException;
import pe.edu.upeu.lamb.curriculo.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.curriculo.mapper.SolicitudModificacionCurricularMapper;
import pe.edu.upeu.lamb.curriculo.repository.PlanEstudioRepository;
import pe.edu.upeu.lamb.curriculo.repository.SolicitudModificacionCurricularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudModificacionCurricularService {

    private final SolicitudModificacionCurricularRepository solicitudModificacionCurricularRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final SolicitudModificacionCurricularMapper solicitudModificacionCurricularMapper;

    @Transactional(readOnly = true)
    public List<SolicitudModificacionCurricularResponse> listar(Long idPlan) {
        List<SolicitudModificacionCurricular> lista = idPlan == null
                ? solicitudModificacionCurricularRepository.findAll()
                : solicitudModificacionCurricularRepository.findByPlanId(idPlan);
        return lista.stream().map(solicitudModificacionCurricularMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudModificacionCurricularResponse obtener(Long id) {
        return solicitudModificacionCurricularMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudModificacionCurricularResponse crear(SolicitudModificacionCurricularRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudModificacionCurricular solicitudModificacionCurricular = solicitudModificacionCurricularMapper.toEntity(request);
        asignarRelaciones(solicitudModificacionCurricular, request);
        solicitudModificacionCurricular.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudModificacionCurricular.setIdSolicitante(idSolicitante);
        solicitudModificacionCurricular.setFechaSolicitud(LocalDateTime.now());
        return solicitudModificacionCurricularMapper.toResponse(solicitudModificacionCurricularRepository.save(solicitudModificacionCurricular));
    }

    @Transactional
    public SolicitudModificacionCurricularResponse actualizar(Long id, SolicitudModificacionCurricularRequest request) {
        SolicitudModificacionCurricular solicitudModificacionCurricular = buscarOFallar(id);
        validarPendiente(solicitudModificacionCurricular);
        validar(request, id);
        solicitudModificacionCurricular.setTipo(request.getTipo());
        solicitudModificacionCurricular.setDescripcion(request.getDescripcion().trim());
        solicitudModificacionCurricular.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudModificacionCurricular, request);
        return solicitudModificacionCurricularMapper.toResponse(solicitudModificacionCurricularRepository.save(solicitudModificacionCurricular));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudModificacionCurricular solicitudModificacionCurricular = buscarOFallar(id);
        validarPendiente(solicitudModificacionCurricular);
        solicitudModificacionCurricularRepository.delete(solicitudModificacionCurricular);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudModificacionCurricularResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudModificacionCurricular solicitudModificacionCurricular = buscarOFallar(id);
        validarPendiente(solicitudModificacionCurricular);
        solicitudModificacionCurricular.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudModificacionCurricular.setObservacionRespuesta(request.getObservacion());
        solicitudModificacionCurricular.setIdRespondidoPor(idResponsable);
        solicitudModificacionCurricular.setFechaRespuesta(LocalDateTime.now());
        return solicitudModificacionCurricularMapper.toResponse(solicitudModificacionCurricularRepository.save(solicitudModificacionCurricular));
    }

    SolicitudModificacionCurricular buscarOFallar(Long id) {
        return solicitudModificacionCurricularRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de modificacion curricular no encontrada: " + id));
    }

    private void validar(SolicitudModificacionCurricularRequest request, Long id) {
    }

    private void asignarRelaciones(SolicitudModificacionCurricular solicitudModificacionCurricular, SolicitudModificacionCurricularRequest request) {
        solicitudModificacionCurricular.setPlan(planEstudioRepository.findById(request.getIdPlan())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudio no encontrado: " + request.getIdPlan())));
    }

    private void validarPendiente(SolicitudModificacionCurricular solicitudModificacionCurricular) {
        if (solicitudModificacionCurricular.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudModificacionCurricular.getId() + " esta " + solicitudModificacionCurricular.getEstado() + " y ya no se puede modificar");
        }
    }
}
