package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioHorarioRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioHorarioResponse;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioHorario;
import org.springframework.stereotype.Component;

@Component
public class SolicitudCambioHorarioMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudCambioHorario toEntity(SolicitudCambioHorarioRequest request) {
        return SolicitudCambioHorario.builder()
                .diaSemana(request.getDiaSemana())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudCambioHorarioResponse toResponse(SolicitudCambioHorario solicitudCambioHorario) {
        return SolicitudCambioHorarioResponse.builder()
                .id(solicitudCambioHorario.getId())
                .idHorario(solicitudCambioHorario.getHorario().getId())
                .diaSemana(solicitudCambioHorario.getDiaSemana())
                .horaInicio(solicitudCambioHorario.getHoraInicio())
                .horaFin(solicitudCambioHorario.getHoraFin())
                .justificacion(solicitudCambioHorario.getJustificacion())
                .idSolicitante(solicitudCambioHorario.getIdSolicitante())
                .estado(solicitudCambioHorario.getEstado())
                .observacionRespuesta(solicitudCambioHorario.getObservacionRespuesta())
                .idRespondidoPor(solicitudCambioHorario.getIdRespondidoPor())
                .fechaSolicitud(solicitudCambioHorario.getFechaSolicitud())
                .fechaRespuesta(solicitudCambioHorario.getFechaRespuesta())
                .build();
    }
}
