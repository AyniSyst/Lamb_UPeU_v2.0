package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.CalificacionRequest;
import pe.edu.upeu.lms.dto.CalificacionResponse;
import pe.edu.upeu.lms.entity.Calificacion;

@Mapper(componentModel = "spring", uses = EvaluacionMapper.class)
public interface CalificacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evaluacion", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Calificacion toEntity(CalificacionRequest request);

    CalificacionResponse toResponse(Calificacion calificacion);
}
