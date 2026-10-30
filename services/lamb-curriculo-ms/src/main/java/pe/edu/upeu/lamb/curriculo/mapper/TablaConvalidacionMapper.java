package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.TablaConvalidacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.TablaConvalidacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.TablaConvalidacion;
import org.springframework.stereotype.Component;

@Component
public class TablaConvalidacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public TablaConvalidacion toEntity(TablaConvalidacionRequest request) {
        return TablaConvalidacion.builder()
                .nombre(request.getNombre().trim())
                .resolucion(request.getResolucion())
                .vigente(request.getVigente())
                .build();
    }

    public TablaConvalidacionResponse toResponse(TablaConvalidacion tablaConvalidacion) {
        return TablaConvalidacionResponse.builder()
                .id(tablaConvalidacion.getId())
                .idUniversidad(tablaConvalidacion.getUniversidad().getId())
                .codigoUniversidad(tablaConvalidacion.getUniversidad().getCodigo())
                .idPlan(tablaConvalidacion.getPlan().getId())
                .versionPlan(tablaConvalidacion.getPlan().getVersion())
                .nombre(tablaConvalidacion.getNombre())
                .resolucion(tablaConvalidacion.getResolucion())
                .vigente(tablaConvalidacion.getVigente())
                .build();
    }
}
