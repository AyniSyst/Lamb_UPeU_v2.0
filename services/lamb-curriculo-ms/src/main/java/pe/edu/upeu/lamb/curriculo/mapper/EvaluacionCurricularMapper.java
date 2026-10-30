package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.EvaluacionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.EvaluacionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.EvaluacionCurricular;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionCurricularMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EvaluacionCurricular toEntity(EvaluacionCurricularRequest request) {
        return EvaluacionCurricular.builder()
                .idPeriodo(request.getIdPeriodo())
                .fecha(request.getFecha())
                .resultado(request.getResultado())
                .conclusiones(request.getConclusiones())
                .build();
    }

    public EvaluacionCurricularResponse toResponse(EvaluacionCurricular evaluacionCurricular) {
        return EvaluacionCurricularResponse.builder()
                .id(evaluacionCurricular.getId())
                .idPlan(evaluacionCurricular.getPlan().getId())
                .versionPlan(evaluacionCurricular.getPlan().getVersion())
                .idComision(evaluacionCurricular.getComision() != null ? evaluacionCurricular.getComision().getId() : null)
                .nombreComision(evaluacionCurricular.getComision() != null ? evaluacionCurricular.getComision().getNombre() : null)
                .idPeriodo(evaluacionCurricular.getIdPeriodo())
                .fecha(evaluacionCurricular.getFecha())
                .resultado(evaluacionCurricular.getResultado())
                .conclusiones(evaluacionCurricular.getConclusiones())
                .build();
    }
}
