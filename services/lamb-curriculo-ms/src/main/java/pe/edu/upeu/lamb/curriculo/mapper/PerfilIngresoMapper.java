package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.PerfilIngresoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PerfilIngresoResponse;
import pe.edu.upeu.lamb.curriculo.entity.PerfilIngreso;
import org.springframework.stereotype.Component;

@Component
public class PerfilIngresoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PerfilIngreso toEntity(PerfilIngresoRequest request) {
        return PerfilIngreso.builder()
                .descripcion(request.getDescripcion().trim())
                .conocimientos(request.getConocimientos())
                .habilidades(request.getHabilidades())
                .actitudes(request.getActitudes())
                .build();
    }

    public PerfilIngresoResponse toResponse(PerfilIngreso perfilIngreso) {
        return PerfilIngresoResponse.builder()
                .id(perfilIngreso.getId())
                .idPlan(perfilIngreso.getPlan().getId())
                .versionPlan(perfilIngreso.getPlan().getVersion())
                .descripcion(perfilIngreso.getDescripcion())
                .conocimientos(perfilIngreso.getConocimientos())
                .habilidades(perfilIngreso.getHabilidades())
                .actitudes(perfilIngreso.getActitudes())
                .build();
    }
}
