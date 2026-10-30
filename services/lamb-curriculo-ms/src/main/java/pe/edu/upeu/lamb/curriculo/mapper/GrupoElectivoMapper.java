package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.GrupoElectivoRequest;
import pe.edu.upeu.lamb.curriculo.dto.GrupoElectivoResponse;
import pe.edu.upeu.lamb.curriculo.entity.GrupoElectivo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GrupoElectivoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public GrupoElectivo toEntity(GrupoElectivoRequest request) {
        return GrupoElectivo.builder()
                .nombre(request.getNombre().trim())
                .cantidadElegir(request.getCantidadElegir())
                .idPlanCursos(new ArrayList<>(request.getIdPlanCursos()))
                .build();
    }

    public GrupoElectivoResponse toResponse(GrupoElectivo grupoElectivo) {
        return GrupoElectivoResponse.builder()
                .id(grupoElectivo.getId())
                .idPlan(grupoElectivo.getPlan().getId())
                .versionPlan(grupoElectivo.getPlan().getVersion())
                .nombre(grupoElectivo.getNombre())
                .cantidadElegir(grupoElectivo.getCantidadElegir())
                .idPlanCursos(List.copyOf(grupoElectivo.getIdPlanCursos()))
                .build();
    }
}
