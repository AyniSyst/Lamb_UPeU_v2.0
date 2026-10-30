package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.ComponenteEvaluacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ComponenteEvaluacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ComponenteEvaluacion;
import org.springframework.stereotype.Component;

@Component
public class ComponenteEvaluacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ComponenteEvaluacion toEntity(ComponenteEvaluacionRequest request) {
        return ComponenteEvaluacion.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .tipo(request.getTipo())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ComponenteEvaluacionResponse toResponse(ComponenteEvaluacion componenteEvaluacion) {
        return ComponenteEvaluacionResponse.builder()
                .id(componenteEvaluacion.getId())
                .codigo(componenteEvaluacion.getCodigo())
                .nombre(componenteEvaluacion.getNombre())
                .tipo(componenteEvaluacion.getTipo())
                .descripcion(componenteEvaluacion.getDescripcion())
                .build();
    }
}
