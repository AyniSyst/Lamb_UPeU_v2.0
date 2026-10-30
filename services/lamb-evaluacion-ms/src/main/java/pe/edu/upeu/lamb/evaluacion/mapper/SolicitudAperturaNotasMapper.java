package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.SolicitudAperturaNotasRequest;
import pe.edu.upeu.lamb.evaluacion.dto.SolicitudAperturaNotasResponse;
import pe.edu.upeu.lamb.evaluacion.entity.SolicitudAperturaNotas;
import org.springframework.stereotype.Component;

@Component
public class SolicitudAperturaNotasMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudAperturaNotas toEntity(SolicitudAperturaNotasRequest request) {
        return SolicitudAperturaNotas.builder()
                .idGrupo(request.getIdGrupo())
                .motivo(request.getMotivo().trim())
                .fechaInicioPropuesta(request.getFechaInicioPropuesta())
                .fechaFinPropuesta(request.getFechaFinPropuesta())
                .build();
    }

    public SolicitudAperturaNotasResponse toResponse(SolicitudAperturaNotas solicitudAperturaNotas) {
        return SolicitudAperturaNotasResponse.builder()
                .id(solicitudAperturaNotas.getId())
                .idGrupo(solicitudAperturaNotas.getIdGrupo())
                .motivo(solicitudAperturaNotas.getMotivo())
                .fechaInicioPropuesta(solicitudAperturaNotas.getFechaInicioPropuesta())
                .fechaFinPropuesta(solicitudAperturaNotas.getFechaFinPropuesta())
                .idSolicitante(solicitudAperturaNotas.getIdSolicitante())
                .estado(solicitudAperturaNotas.getEstado())
                .observacionRespuesta(solicitudAperturaNotas.getObservacionRespuesta())
                .idRespondidoPor(solicitudAperturaNotas.getIdRespondidoPor())
                .fechaSolicitud(solicitudAperturaNotas.getFechaSolicitud())
                .fechaRespuesta(solicitudAperturaNotas.getFechaRespuesta())
                .build();
    }
}
