package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.PreguntaRequest;
import pe.edu.upeu.lms.dto.PreguntaResponse;
import pe.edu.upeu.lms.entity.Pregunta;

@Mapper(componentModel = "spring", uses = EvaluacionMapper.class)
public interface PreguntaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evaluacion", ignore = true)
    Pregunta toEntity(PreguntaRequest request);

    PreguntaResponse toResponse(Pregunta pregunta);
}
