package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SolicitudGrupoEquivalenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudGrupoEquivalenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudGrupoEquivalente;
import org.springframework.stereotype.Component;

@Component
public class SolicitudGrupoEquivalenteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudGrupoEquivalente toEntity(SolicitudGrupoEquivalenteRequest request) {
        return SolicitudGrupoEquivalente.builder()
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudGrupoEquivalenteResponse toResponse(SolicitudGrupoEquivalente solicitudGrupoEquivalente) {
        return SolicitudGrupoEquivalenteResponse.builder()
                .id(solicitudGrupoEquivalente.getId())
                .idGrupoHabilitado(solicitudGrupoEquivalente.getGrupoHabilitado().getId())
                .justificacion(solicitudGrupoEquivalente.getJustificacion())
                .idSolicitante(solicitudGrupoEquivalente.getIdSolicitante())
                .estado(solicitudGrupoEquivalente.getEstado())
                .observacionRespuesta(solicitudGrupoEquivalente.getObservacionRespuesta())
                .idRespondidoPor(solicitudGrupoEquivalente.getIdRespondidoPor())
                .fechaSolicitud(solicitudGrupoEquivalente.getFechaSolicitud())
                .fechaRespuesta(solicitudGrupoEquivalente.getFechaRespuesta())
                .build();
    }
}
