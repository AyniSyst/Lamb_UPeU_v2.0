package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.InsigniaEstudianteRequest;
import pe.edu.upeu.lms.dto.InsigniaEstudianteResponse;
import pe.edu.upeu.lms.entity.InsigniaEstudiante;

@Mapper(componentModel = "spring", uses = InsigniaMapper.class)
public interface InsigniaEstudianteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insignia", ignore = true)
    @Mapping(target = "fechaOtorgamiento", ignore = true)
    InsigniaEstudiante toEntity(InsigniaEstudianteRequest request);

    InsigniaEstudianteResponse toResponse(InsigniaEstudiante insigniaEstudiante);
}
