package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.RecursoComplementarioRequest;
import pe.edu.upeu.lms.dto.RecursoComplementarioResponse;
import pe.edu.upeu.lms.entity.RecursoComplementario;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface RecursoComplementarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    RecursoComplementario toEntity(RecursoComplementarioRequest request);

    RecursoComplementarioResponse toResponse(RecursoComplementario recursoComplementario);
}
