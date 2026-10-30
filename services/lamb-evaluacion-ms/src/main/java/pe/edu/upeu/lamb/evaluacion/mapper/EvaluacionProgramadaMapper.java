package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.EvaluacionProgramadaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EvaluacionProgramadaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EvaluacionProgramada;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionProgramadaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EvaluacionProgramada toEntity(EvaluacionProgramadaRequest request) {
        return EvaluacionProgramada.builder()
                .idGrupo(request.getIdGrupo())
                .fecha(request.getFecha())
                .descripcion(request.getDescripcion())
                .build();
    }

    public EvaluacionProgramadaResponse toResponse(EvaluacionProgramada evaluacionProgramada) {
        return EvaluacionProgramadaResponse.builder()
                .id(evaluacionProgramada.getId())
                .idGrupo(evaluacionProgramada.getIdGrupo())
                .idComponente(evaluacionProgramada.getComponente().getId())
                .codigoComponente(evaluacionProgramada.getComponente().getCodigo())
                .fecha(evaluacionProgramada.getFecha())
                .descripcion(evaluacionProgramada.getDescripcion())
                .build();
    }
}
