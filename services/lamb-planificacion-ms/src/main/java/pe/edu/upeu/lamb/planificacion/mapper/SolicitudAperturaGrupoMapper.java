package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SolicitudAperturaGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudAperturaGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudAperturaGrupo;
import org.springframework.stereotype.Component;

@Component
public class SolicitudAperturaGrupoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudAperturaGrupo toEntity(SolicitudAperturaGrupoRequest request) {
        return SolicitudAperturaGrupo.builder()
                .cupoPropuesto(request.getCupoPropuesto())
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudAperturaGrupoResponse toResponse(SolicitudAperturaGrupo solicitudAperturaGrupo) {
        return SolicitudAperturaGrupoResponse.builder()
                .id(solicitudAperturaGrupo.getId())
                .idOferta(solicitudAperturaGrupo.getOferta().getId())
                .cupoPropuesto(solicitudAperturaGrupo.getCupoPropuesto())
                .justificacion(solicitudAperturaGrupo.getJustificacion())
                .idSolicitante(solicitudAperturaGrupo.getIdSolicitante())
                .estado(solicitudAperturaGrupo.getEstado())
                .observacionRespuesta(solicitudAperturaGrupo.getObservacionRespuesta())
                .idRespondidoPor(solicitudAperturaGrupo.getIdRespondidoPor())
                .fechaSolicitud(solicitudAperturaGrupo.getFechaSolicitud())
                .fechaRespuesta(solicitudAperturaGrupo.getFechaRespuesta())
                .build();
    }
}
