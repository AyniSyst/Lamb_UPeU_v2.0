package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.ConvalidacionCursoRequest;
import pe.edu.upeu.lms.dto.ConvalidacionCursoResponse;
import pe.edu.upeu.lms.entity.ConvalidacionCurso;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface ConvalidacionCursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "estado", ignore = true)
    ConvalidacionCurso toEntity(ConvalidacionCursoRequest request);

    ConvalidacionCursoResponse toResponse(ConvalidacionCurso convalidacionCurso);
}
