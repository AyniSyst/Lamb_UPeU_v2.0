package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CicloPlanRequest;
import pe.edu.upeu.lamb.curriculo.dto.CicloPlanResponse;
import pe.edu.upeu.lamb.curriculo.entity.CicloPlan;
import org.springframework.stereotype.Component;

@Component
public class CicloPlanMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CicloPlan toEntity(CicloPlanRequest request) {
        return CicloPlan.builder()
                .numero(request.getNumero())
                .nombre(request.getNombre().trim())
                .build();
    }

    public CicloPlanResponse toResponse(CicloPlan cicloPlan) {
        return CicloPlanResponse.builder()
                .id(cicloPlan.getId())
                .idPlan(cicloPlan.getPlan().getId())
                .versionPlan(cicloPlan.getPlan().getVersion())
                .numero(cicloPlan.getNumero())
                .nombre(cicloPlan.getNombre())
                .build();
    }
}
