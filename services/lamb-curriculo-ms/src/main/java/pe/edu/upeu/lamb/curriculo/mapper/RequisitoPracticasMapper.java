package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoPracticasRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoPracticasResponse;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoPracticas;
import org.springframework.stereotype.Component;

@Component
public class RequisitoPracticasMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RequisitoPracticas toEntity(RequisitoPracticasRequest request) {
        return RequisitoPracticas.builder()
                .horasMinimas(request.getHorasMinimas())
                .cicloDesde(request.getCicloDesde())
                .descripcion(request.getDescripcion())
                .build();
    }

    public RequisitoPracticasResponse toResponse(RequisitoPracticas requisitoPracticas) {
        return RequisitoPracticasResponse.builder()
                .id(requisitoPracticas.getId())
                .idPlan(requisitoPracticas.getPlan().getId())
                .versionPlan(requisitoPracticas.getPlan().getVersion())
                .horasMinimas(requisitoPracticas.getHorasMinimas())
                .cicloDesde(requisitoPracticas.getCicloDesde())
                .descripcion(requisitoPracticas.getDescripcion())
                .build();
    }
}
