package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.EjeCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.EjeCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.EjeCurricular;
import org.springframework.stereotype.Component;

@Component
public class EjeCurricularMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EjeCurricular toEntity(EjeCurricularRequest request) {
        return EjeCurricular.builder()
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public EjeCurricularResponse toResponse(EjeCurricular ejeCurricular) {
        return EjeCurricularResponse.builder()
                .id(ejeCurricular.getId())
                .idPlan(ejeCurricular.getPlan().getId())
                .versionPlan(ejeCurricular.getPlan().getVersion())
                .nombre(ejeCurricular.getNombre())
                .descripcion(ejeCurricular.getDescripcion())
                .build();
    }
}
