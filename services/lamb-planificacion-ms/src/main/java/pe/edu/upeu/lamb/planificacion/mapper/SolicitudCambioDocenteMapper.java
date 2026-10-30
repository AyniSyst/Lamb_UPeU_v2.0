package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.SolicitudCambioDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioDocente;
import org.springframework.stereotype.Component;

@Component
public class SolicitudCambioDocenteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SolicitudCambioDocente toEntity(SolicitudCambioDocenteRequest request) {
        return SolicitudCambioDocente.builder()
                .idDocenteNuevo(request.getIdDocenteNuevo())
                .justificacion(request.getJustificacion().trim())
                .build();
    }

    public SolicitudCambioDocenteResponse toResponse(SolicitudCambioDocente solicitudCambioDocente) {
        return SolicitudCambioDocenteResponse.builder()
                .id(solicitudCambioDocente.getId())
                .idAsignacion(solicitudCambioDocente.getAsignacion().getId())
                .idDocenteNuevo(solicitudCambioDocente.getIdDocenteNuevo())
                .justificacion(solicitudCambioDocente.getJustificacion())
                .idSolicitante(solicitudCambioDocente.getIdSolicitante())
                .estado(solicitudCambioDocente.getEstado())
                .observacionRespuesta(solicitudCambioDocente.getObservacionRespuesta())
                .idRespondidoPor(solicitudCambioDocente.getIdRespondidoPor())
                .fechaSolicitud(solicitudCambioDocente.getFechaSolicitud())
                .fechaRespuesta(solicitudCambioDocente.getFechaRespuesta())
                .build();
    }
}
