package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.RubricaEvaluacionRequest;
import pe.edu.upeu.lms.dto.RubricaEvaluacionResponse;
import pe.edu.upeu.lms.entity.RubricaEvaluacion;

@Mapper(componentModel = "spring")
public interface RubricaEvaluacionMapper {

    RubricaEvaluacionResponse toResponse(RubricaEvaluacion rubricaEvaluacion);

    @Mapping(target = "id", ignore = true)
    RubricaEvaluacion toEntity(RubricaEvaluacionRequest request);
}
