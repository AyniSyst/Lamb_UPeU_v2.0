package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CompetenciaGeneralRequest;
import pe.edu.upeu.lamb.curriculo.dto.CompetenciaGeneralResponse;
import pe.edu.upeu.lamb.curriculo.entity.CompetenciaGeneral;
import org.springframework.stereotype.Component;

@Component
public class CompetenciaGeneralMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CompetenciaGeneral toEntity(CompetenciaGeneralRequest request) {
        return CompetenciaGeneral.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public CompetenciaGeneralResponse toResponse(CompetenciaGeneral competenciaGeneral) {
        return CompetenciaGeneralResponse.builder()
                .id(competenciaGeneral.getId())
                .codigo(competenciaGeneral.getCodigo())
                .nombre(competenciaGeneral.getNombre())
                .descripcion(competenciaGeneral.getDescripcion())
                .build();
    }
}
