package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.SolicitudModificacionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.SolicitudModificacionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.SolicitudModificacionCurricular;
import org.springframework.stereotype.Component;

@Component
public class SolicitudModificacionCurricularMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudModificacionCurricular toEntity(SolicitudModificacionCurricularRequest request) {
        return SolicitudModificacionCurricular.builder()
                .tipo(request.getTipo())
                .descripcion(request.getDescripcion().trim())
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudModificacionCurricularResponse toResponse(SolicitudModificacionCurricular solicitudModificacionCurricular) {
        return SolicitudModificacionCurricularResponse.builder()
                .id(solicitudModificacionCurricular.getId())
                .idPlan(solicitudModificacionCurricular.getPlan().getId())
                .versionPlan(solicitudModificacionCurricular.getPlan().getVersion())
                .tipo(solicitudModificacionCurricular.getTipo())
                .descripcion(solicitudModificacionCurricular.getDescripcion())
                .justificacion(solicitudModificacionCurricular.getJustificacion())
                .idSolicitante(solicitudModificacionCurricular.getIdSolicitante())
                .estado(solicitudModificacionCurricular.getEstado())
                .observacionRespuesta(solicitudModificacionCurricular.getObservacionRespuesta())
                .idRespondidoPor(solicitudModificacionCurricular.getIdRespondidoPor())
                .fechaSolicitud(solicitudModificacionCurricular.getFechaSolicitud())
                .fechaRespuesta(solicitudModificacionCurricular.getFechaRespuesta())
                .build();
    }
}
