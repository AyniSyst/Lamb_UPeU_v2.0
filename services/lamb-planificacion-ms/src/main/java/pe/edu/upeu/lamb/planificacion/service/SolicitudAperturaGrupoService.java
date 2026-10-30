package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudAperturaGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudAperturaGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.OfertaAcademica;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudAperturaGrupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SolicitudAperturaGrupoMapper;
import pe.edu.upeu.lamb.planificacion.repository.OfertaAcademicaRepository;
import pe.edu.upeu.lamb.planificacion.repository.SolicitudAperturaGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudAperturaGrupoService {

    private final SolicitudAperturaGrupoRepository solicitudAperturaGrupoRepository;
    private final OfertaAcademicaRepository ofertaAcademicaRepository;
    private final SolicitudAperturaGrupoMapper solicitudAperturaGrupoMapper;

    @Transactional(readOnly = true)
    public List<SolicitudAperturaGrupoResponse> listar(Long idOferta) {
        List<SolicitudAperturaGrupo> lista = idOferta == null
                ? solicitudAperturaGrupoRepository.findAll()
                : solicitudAperturaGrupoRepository.findByOfertaId(idOferta);
        return lista.stream().map(solicitudAperturaGrupoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudAperturaGrupoResponse obtener(Long id) {
        return solicitudAperturaGrupoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudAperturaGrupoResponse crear(SolicitudAperturaGrupoRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudAperturaGrupo solicitudAperturaGrupo = solicitudAperturaGrupoMapper.toEntity(request);
        asignarRelaciones(solicitudAperturaGrupo, request);
        solicitudAperturaGrupo.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudAperturaGrupo.setIdSolicitante(idSolicitante);
        solicitudAperturaGrupo.setFechaSolicitud(LocalDateTime.now());
        return solicitudAperturaGrupoMapper.toResponse(solicitudAperturaGrupoRepository.save(solicitudAperturaGrupo));
    }

    @Transactional
    public SolicitudAperturaGrupoResponse actualizar(Long id, SolicitudAperturaGrupoRequest request) {
        SolicitudAperturaGrupo solicitudAperturaGrupo = buscarOFallar(id);
        validarPendiente(solicitudAperturaGrupo);
        validar(request, id);
        solicitudAperturaGrupo.setCupoPropuesto(request.getCupoPropuesto());
        solicitudAperturaGrupo.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudAperturaGrupo, request);
        return solicitudAperturaGrupoMapper.toResponse(solicitudAperturaGrupoRepository.save(solicitudAperturaGrupo));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudAperturaGrupo solicitudAperturaGrupo = buscarOFallar(id);
        validarPendiente(solicitudAperturaGrupo);
        solicitudAperturaGrupoRepository.delete(solicitudAperturaGrupo);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudAperturaGrupoResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudAperturaGrupo solicitudAperturaGrupo = buscarOFallar(id);
        validarPendiente(solicitudAperturaGrupo);
        solicitudAperturaGrupo.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudAperturaGrupo.setObservacionRespuesta(request.getObservacion());
        solicitudAperturaGrupo.setIdRespondidoPor(idResponsable);
        solicitudAperturaGrupo.setFechaRespuesta(LocalDateTime.now());
        return solicitudAperturaGrupoMapper.toResponse(solicitudAperturaGrupoRepository.save(solicitudAperturaGrupo));
    }

    SolicitudAperturaGrupo buscarOFallar(Long id) {
        return solicitudAperturaGrupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de apertura de grupo no encontrada: " + id));
    }

    private void validar(SolicitudAperturaGrupoRequest request, Long id) {
    }

    private void asignarRelaciones(SolicitudAperturaGrupo solicitudAperturaGrupo, SolicitudAperturaGrupoRequest request) {
        solicitudAperturaGrupo.setOferta(ofertaAcademicaRepository.findById(request.getIdOferta())
                .orElseThrow(() -> new ResourceNotFoundException("Oferta academica no encontrada: " + request.getIdOferta())));
    }

    private void validarPendiente(SolicitudAperturaGrupo solicitudAperturaGrupo) {
        if (solicitudAperturaGrupo.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudAperturaGrupo.getId() + " esta " + solicitudAperturaGrupo.getEstado() + " y ya no se puede modificar");
        }
    }
}
