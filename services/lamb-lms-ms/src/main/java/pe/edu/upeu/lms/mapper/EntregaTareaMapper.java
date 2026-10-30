package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.EntregaTareaRequest;
import pe.edu.upeu.lms.dto.EntregaTareaResponse;
import pe.edu.upeu.lms.entity.EntregaTarea;

@Mapper(componentModel = "spring", uses = TareaMapper.class)
public interface EntregaTareaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tarea", ignore = true)
    @Mapping(target = "fechaEntrega", ignore = true)
    EntregaTarea toEntity(EntregaTareaRequest request);

    EntregaTareaResponse toResponse(EntregaTarea entregaTarea);
}
