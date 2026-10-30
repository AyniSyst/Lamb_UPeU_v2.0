package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SolicitudCierreFusionGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCierreFusionGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCierreFusionGrupo;
import org.springframework.stereotype.Component;

@Component
public class SolicitudCierreFusionGrupoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudCierreFusionGrupo toEntity(SolicitudCierreFusionGrupoRequest request) {
        return SolicitudCierreFusionGrupo.builder()
                .tipo(request.getTipo())
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudCierreFusionGrupoResponse toResponse(SolicitudCierreFusionGrupo solicitudCierreFusionGrupo) {
        return SolicitudCierreFusionGrupoResponse.builder()
                .id(solicitudCierreFusionGrupo.getId())
                .tipo(solicitudCierreFusionGrupo.getTipo())
                .idGrupo(solicitudCierreFusionGrupo.getGrupo().getId())
                .codigoGrupo(solicitudCierreFusionGrupo.getGrupo().getCodigo())
                .idGrupoDestino(solicitudCierreFusionGrupo.getGrupoDestino() != null ? solicitudCierreFusionGrupo.getGrupoDestino().getId() : null)
                .codigoGrupoDestino(solicitudCierreFusionGrupo.getGrupoDestino() != null ? solicitudCierreFusionGrupo.getGrupoDestino().getCodigo() : null)
                .justificacion(solicitudCierreFusionGrupo.getJustificacion())
                .idSolicitante(solicitudCierreFusionGrupo.getIdSolicitante())
                .estado(solicitudCierreFusionGrupo.getEstado())
                .observacionRespuesta(solicitudCierreFusionGrupo.getObservacionRespuesta())
                .idRespondidoPor(solicitudCierreFusionGrupo.getIdRespondidoPor())
                .fechaSolicitud(solicitudCierreFusionGrupo.getFechaSolicitud())
                .fechaRespuesta(solicitudCierreFusionGrupo.getFechaRespuesta())
                .build();
    }
}
