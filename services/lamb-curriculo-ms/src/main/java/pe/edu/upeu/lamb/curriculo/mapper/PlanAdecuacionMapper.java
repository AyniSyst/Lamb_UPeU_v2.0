package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.PlanAdecuacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanAdecuacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.PlanAdecuacion;
import org.springframework.stereotype.Component;

@Component
public class PlanAdecuacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PlanAdecuacion toEntity(PlanAdecuacionRequest request) {
        return PlanAdecuacion.builder()
                .nombre(request.getNombre().trim())
                .resolucion(request.getResolucion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .descripcion(request.getDescripcion())
                .build();
    }

    public PlanAdecuacionResponse toResponse(PlanAdecuacion planAdecuacion) {
        return PlanAdecuacionResponse.builder()
                .id(planAdecuacion.getId())
                .idPlanOrigen(planAdecuacion.getPlanOrigen().getId())
                .versionPlanOrigen(planAdecuacion.getPlanOrigen().getVersion())
                .idPlanDestino(planAdecuacion.getPlanDestino().getId())
                .versionPlanDestino(planAdecuacion.getPlanDestino().getVersion())
                .nombre(planAdecuacion.getNombre())
                .resolucion(planAdecuacion.getResolucion())
                .fechaInicio(planAdecuacion.getFechaInicio())
                .fechaFin(planAdecuacion.getFechaFin())
                .descripcion(planAdecuacion.getDescripcion())
                .build();
    }
}
