package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.EvaluacionRequest;
import pe.edu.upeu.lms.dto.EvaluacionResponse;
import pe.edu.upeu.lms.entity.Evaluacion;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface EvaluacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Evaluacion toEntity(EvaluacionRequest request);

    EvaluacionResponse toResponse(Evaluacion evaluacion);
}
