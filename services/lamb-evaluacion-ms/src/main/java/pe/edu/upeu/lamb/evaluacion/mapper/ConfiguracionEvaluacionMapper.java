package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.ConfiguracionEvaluacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ConfiguracionEvaluacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ConfiguracionEvaluacion;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracionEvaluacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ConfiguracionEvaluacion toEntity(ConfiguracionEvaluacionRequest request) {
        return ConfiguracionEvaluacion.builder()
                .idGrupo(request.getIdGrupo())
                .peso(request.getPeso())
                .build();
    }

    public ConfiguracionEvaluacionResponse toResponse(ConfiguracionEvaluacion configuracionEvaluacion) {
        return ConfiguracionEvaluacionResponse.builder()
                .id(configuracionEvaluacion.getId())
                .idGrupo(configuracionEvaluacion.getIdGrupo())
                .idComponente(configuracionEvaluacion.getComponente().getId())
                .codigoComponente(configuracionEvaluacion.getComponente().getCodigo())
                .peso(configuracionEvaluacion.getPeso())
                .build();
    }
}
