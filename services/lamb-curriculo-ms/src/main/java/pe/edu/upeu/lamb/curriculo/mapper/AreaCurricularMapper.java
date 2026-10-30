package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.AreaCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.AreaCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.AreaCurricular;
import org.springframework.stereotype.Component;

@Component
public class AreaCurricularMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public AreaCurricular toEntity(AreaCurricularRequest request) {
        return AreaCurricular.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public AreaCurricularResponse toResponse(AreaCurricular areaCurricular) {
        return AreaCurricularResponse.builder()
                .id(areaCurricular.getId())
                .codigo(areaCurricular.getCodigo())
                .nombre(areaCurricular.getNombre())
                .descripcion(areaCurricular.getDescripcion())
                .build();
    }
}
