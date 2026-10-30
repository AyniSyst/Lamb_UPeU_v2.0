package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.PerfilEgresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PerfilEgresoResponse;
import pe.edu.upeu.lamb.curriculo.entity.PerfilEgreso;
import org.springframework.stereotype.Component;

@Component
public class PerfilEgresoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PerfilEgreso toEntity(PerfilEgresoRequest request) {
        return PerfilEgreso.builder()
                .descripcion(request.getDescripcion().trim())
                .build();
    }

    public PerfilEgresoResponse toResponse(PerfilEgreso perfilEgreso) {
        return PerfilEgresoResponse.builder()
                .id(perfilEgreso.getId())
                .idPlan(perfilEgreso.getPlan().getId())
                .versionPlan(perfilEgreso.getPlan().getVersion())
                .descripcion(perfilEgreso.getDescripcion())
                .build();
    }
}
