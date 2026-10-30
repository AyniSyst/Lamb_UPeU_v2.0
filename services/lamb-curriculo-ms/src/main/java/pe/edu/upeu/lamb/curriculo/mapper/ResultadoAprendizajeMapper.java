package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ResultadoAprendizajeRequest;
import pe.edu.upeu.lamb.curriculo.dto.ResultadoAprendizajeResponse;
import pe.edu.upeu.lamb.curriculo.entity.ResultadoAprendizaje;
import org.springframework.stereotype.Component;

@Component
public class ResultadoAprendizajeMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ResultadoAprendizaje toEntity(ResultadoAprendizajeRequest request) {
        return ResultadoAprendizaje.builder()
                .codigo(request.getCodigo().trim())
                .descripcion(request.getDescripcion().trim())
                .build();
    }

    public ResultadoAprendizajeResponse toResponse(ResultadoAprendizaje resultadoAprendizaje) {
        return ResultadoAprendizajeResponse.builder()
                .id(resultadoAprendizaje.getId())
                .idCurso(resultadoAprendizaje.getCurso().getId())
                .codigoCurso(resultadoAprendizaje.getCurso().getCodigo())
                .codigo(resultadoAprendizaje.getCodigo())
                .descripcion(resultadoAprendizaje.getDescripcion())
                .build();
    }
}
