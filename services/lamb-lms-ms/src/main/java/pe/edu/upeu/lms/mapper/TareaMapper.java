package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.TareaRequest;
import pe.edu.upeu.lms.dto.TareaResponse;
import pe.edu.upeu.lms.entity.Tarea;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface TareaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Tarea toEntity(TareaRequest request);

    TareaResponse toResponse(Tarea tarea);
}
