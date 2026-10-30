package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.MencionRequest;
import pe.edu.upeu.lamb.curriculo.dto.MencionResponse;
import pe.edu.upeu.lamb.curriculo.entity.Mencion;
import org.springframework.stereotype.Component;

@Component
public class MencionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public Mencion toEntity(MencionRequest request) {
        return Mencion.builder()
                .nombre(request.getNombre().trim())
                .creditosRequeridos(request.getCreditosRequeridos())
                .descripcion(request.getDescripcion())
                .build();
    }

    public MencionResponse toResponse(Mencion mencion) {
        return MencionResponse.builder()
                .id(mencion.getId())
                .idPlan(mencion.getPlan().getId())
                .versionPlan(mencion.getPlan().getVersion())
                .nombre(mencion.getNombre())
                .creditosRequeridos(mencion.getCreditosRequeridos())
                .descripcion(mencion.getDescripcion())
                .build();
    }
}
