package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioAulaRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioAulaResponse;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioAula;
import org.springframework.stereotype.Component;

@Component
public class SolicitudCambioAulaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudCambioAula toEntity(SolicitudCambioAulaRequest request) {
        return SolicitudCambioAula.builder()
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudCambioAulaResponse toResponse(SolicitudCambioAula solicitudCambioAula) {
        return SolicitudCambioAulaResponse.builder()
                .id(solicitudCambioAula.getId())
                .idHorario(solicitudCambioAula.getHorario().getId())
                .idAmbienteNuevo(solicitudCambioAula.getAmbienteNuevo().getId())
                .codigoAmbienteNuevo(solicitudCambioAula.getAmbienteNuevo().getCodigo())
                .justificacion(solicitudCambioAula.getJustificacion())
                .idSolicitante(solicitudCambioAula.getIdSolicitante())
                .estado(solicitudCambioAula.getEstado())
                .observacionRespuesta(solicitudCambioAula.getObservacionRespuesta())
                .idRespondidoPor(solicitudCambioAula.getIdRespondidoPor())
                .fechaSolicitud(solicitudCambioAula.getFechaSolicitud())
                .fechaRespuesta(solicitudCambioAula.getFechaRespuesta())
                .build();
    }
}
