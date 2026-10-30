package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.SeccionCursoRequest;
import pe.edu.upeu.lms.dto.SeccionCursoResponse;
import pe.edu.upeu.lms.entity.SeccionCurso;

@Mapper(componentModel = "spring", uses = {CursoMapper.class, DocenteMapper.class, PeriodoAcademicoMapper.class})
public interface SeccionCursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "periodoAcademico", ignore = true)
    SeccionCurso toEntity(SeccionCursoRequest request);

    SeccionCursoResponse toResponse(SeccionCurso seccionCurso);
}
