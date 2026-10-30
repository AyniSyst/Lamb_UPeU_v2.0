package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ResolucionPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.ResolucionPlanResponse;
import pe.edu.upeu.lamb.curriculo.entity.ResolucionPlan;
import org.springframework.stereotype.Component;

@Component
public class ResolucionPlanMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ResolucionPlan toEntity(ResolucionPlanRequest request) {
        return ResolucionPlan.builder()
                .numero(request.getNumero().trim())
                .fecha(request.getFecha())
                .tipo(request.getTipo())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ResolucionPlanResponse toResponse(ResolucionPlan resolucionPlan) {
        return ResolucionPlanResponse.builder()
                .id(resolucionPlan.getId())
                .idPlan(resolucionPlan.getPlan().getId())
                .versionPlan(resolucionPlan.getPlan().getVersion())
                .numero(resolucionPlan.getNumero())
                .fecha(resolucionPlan.getFecha())
                .tipo(resolucionPlan.getTipo())
                .descripcion(resolucionPlan.getDescripcion())
                .build();
    }
}
