package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CronogramaSesionRequest;
import pe.edu.upeu.lamb.planificacion.dto.CronogramaSesionResponse;
import pe.edu.upeu.lamb.planificacion.entity.CronogramaSesion;
import org.springframework.stereotype.Component;

@Component
public class CronogramaSesionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CronogramaSesion toEntity(CronogramaSesionRequest request) {
        return CronogramaSesion.builder()
                .fechaProgramada(request.getFechaProgramada())
                .fechaEjecutada(request.getFechaEjecutada())
                .estado(request.getEstado())
                .observacion(request.getObservacion())
                .build();
    }

    public CronogramaSesionResponse toResponse(CronogramaSesion cronogramaSesion) {
        return CronogramaSesionResponse.builder()
                .id(cronogramaSesion.getId())
                .idSesion(cronogramaSesion.getSesion().getId())
                .temaSesion(cronogramaSesion.getSesion().getTema())
                .fechaProgramada(cronogramaSesion.getFechaProgramada())
                .fechaEjecutada(cronogramaSesion.getFechaEjecutada())
                .estado(cronogramaSesion.getEstado())
                .observacion(cronogramaSesion.getObservacion())
                .build();
    }
}
