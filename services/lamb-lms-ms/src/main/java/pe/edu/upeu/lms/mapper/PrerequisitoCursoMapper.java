package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.PrerequisitoCursoRequest;
import pe.edu.upeu.lms.dto.PrerequisitoCursoResponse;
import pe.edu.upeu.lms.entity.PrerequisitoCurso;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface PrerequisitoCursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "cursoPrerequisito", ignore = true)
    PrerequisitoCurso toEntity(PrerequisitoCursoRequest request);

    PrerequisitoCursoResponse toResponse(PrerequisitoCurso prerequisitoCurso);
}
