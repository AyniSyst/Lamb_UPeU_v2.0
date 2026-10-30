package pe.edu.upeu.lamb.planificacion.service;

import pe.edu.upeu.lamb.planificacion.dto.RespuestaSolicitudRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCierreFusionGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCierreFusionGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.Grupo;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCierreFusionGrupo;
import pe.edu.upeu.lamb.planificacion.exception.BusinessRuleException;
import pe.edu.upeu.lamb.planificacion.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.planificacion.mapper.SolicitudCierreFusionGrupoMapper;
import pe.edu.upeu.lamb.planificacion.repository.GrupoRepository;
import pe.edu.upeu.lamb.planificacion.repository.SolicitudCierreFusionGrupoRepository;
import pe.edu.upeu.lamb.planificacion.entity.TipoCierreFusion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudCierreFusionGrupoService {

    private final SolicitudCierreFusionGrupoRepository solicitudCierreFusionGrupoRepository;
    private final GrupoRepository grupoRepository;
    private final SolicitudCierreFusionGrupoMapper solicitudCierreFusionGrupoMapper;

    @Transactional(readOnly = true)
    public List<SolicitudCierreFusionGrupoResponse> listar(Long idGrupo) {
        List<SolicitudCierreFusionGrupo> lista = idGrupo == null
                ? solicitudCierreFusionGrupoRepository.findAll()
                : solicitudCierreFusionGrupoRepository.findByGrupoId(idGrupo);
        return lista.stream().map(solicitudCierreFusionGrupoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudCierreFusionGrupoResponse obtener(Long id) {
        return solicitudCierreFusionGrupoMapper.toResponse(buscarOFallar(id));
    }

    @Transactional
    public SolicitudCierreFusionGrupoResponse crear(SolicitudCierreFusionGrupoRequest request, Long idSolicitante) {
        validar(request, null);
        SolicitudCierreFusionGrupo solicitudCierreFusionGrupo = solicitudCierreFusionGrupoMapper.toEntity(request);
        asignarRelaciones(solicitudCierreFusionGrupo, request);
        solicitudCierreFusionGrupo.setEstado(EstadoSolicitud.PENDIENTE);
        solicitudCierreFusionGrupo.setIdSolicitante(idSolicitante);
        solicitudCierreFusionGrupo.setFechaSolicitud(LocalDateTime.now());
        return solicitudCierreFusionGrupoMapper.toResponse(solicitudCierreFusionGrupoRepository.save(solicitudCierreFusionGrupo));
    }

    @Transactional
    public SolicitudCierreFusionGrupoResponse actualizar(Long id, SolicitudCierreFusionGrupoRequest request) {
        SolicitudCierreFusionGrupo solicitudCierreFusionGrupo = buscarOFallar(id);
        validarPendiente(solicitudCierreFusionGrupo);
        validar(request, id);
        solicitudCierreFusionGrupo.setTipo(request.getTipo());
        solicitudCierreFusionGrupo.setJustificacion(request.getJustificacion().trim());
        asignarRelaciones(solicitudCierreFusionGrupo, request);
        return solicitudCierreFusionGrupoMapper.toResponse(solicitudCierreFusionGrupoRepository.save(solicitudCierreFusionGrupo));
    }

    @Transactional
    public void eliminar(Long id) {
        SolicitudCierreFusionGrupo solicitudCierreFusionGrupo = buscarOFallar(id);
        validarPendiente(solicitudCierreFusionGrupo);
        solicitudCierreFusionGrupoRepository.delete(solicitudCierreFusionGrupo);
    }

    // Solo una solicitud PENDIENTE se responde; quien responde sale del JWT
    @Transactional
    public SolicitudCierreFusionGrupoResponse responder(Long id, RespuestaSolicitudRequest request, Long idResponsable) {
        SolicitudCierreFusionGrupo solicitudCierreFusionGrupo = buscarOFallar(id);
        validarPendiente(solicitudCierreFusionGrupo);
        solicitudCierreFusionGrupo.setEstado(Boolean.TRUE.equals(request.getAprobada()) ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        solicitudCierreFusionGrupo.setObservacionRespuesta(request.getObservacion());
        solicitudCierreFusionGrupo.setIdRespondidoPor(idResponsable);
        solicitudCierreFusionGrupo.setFechaRespuesta(LocalDateTime.now());
        return solicitudCierreFusionGrupoMapper.toResponse(solicitudCierreFusionGrupoRepository.save(solicitudCierreFusionGrupo));
    }

    SolicitudCierreFusionGrupo buscarOFallar(Long id) {
        return solicitudCierreFusionGrupoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de cierre o fusion de grupos no encontrada: " + id));
    }

    private void validar(SolicitudCierreFusionGrupoRequest request, Long id) {
        if (request.getTipo() == TipoCierreFusion.CIERRE && request.getIdGrupoDestino() != null) {
            throw new BusinessRuleException("Una solicitud de CIERRE no lleva grupo destino");
        }
        if (request.getTipo() == TipoCierreFusion.FUSION) {
            if (request.getIdGrupoDestino() == null || request.getIdGrupoDestino().equals(request.getIdGrupo())) {
                throw new BusinessRuleException("Una FUSION necesita un grupo destino distinto del grupo origen");
            }
            Grupo origen = grupoRepository.findById(request.getIdGrupo())
                    .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo()));
            Grupo destino = grupoRepository.findById(request.getIdGrupoDestino())
                    .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupoDestino()));
            if (!origen.getCurso().getId().equals(destino.getCurso().getId())) {
                throw new BusinessRuleException("Solo se fusionan grupos del mismo curso");
            }
        }
    }

    private void asignarRelaciones(SolicitudCierreFusionGrupo solicitudCierreFusionGrupo, SolicitudCierreFusionGrupoRequest request) {
        solicitudCierreFusionGrupo.setGrupo(grupoRepository.findById(request.getIdGrupo())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupo())));
        solicitudCierreFusionGrupo.setGrupoDestino(request.getIdGrupoDestino() == null ? null : grupoRepository.findById(request.getIdGrupoDestino())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado: " + request.getIdGrupoDestino())));
    }

    private void validarPendiente(SolicitudCierreFusionGrupo solicitudCierreFusionGrupo) {
        if (solicitudCierreFusionGrupo.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessRuleException("La solicitud " + solicitudCierreFusionGrupo.getId() + " esta " + solicitudCierreFusionGrupo.getEstado() + " y ya no se puede modificar");
        }
    }
}
