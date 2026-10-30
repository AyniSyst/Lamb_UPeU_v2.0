package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.CategoriaCursoRequest;
import pe.edu.upeu.lms.dto.CategoriaCursoResponse;
import pe.edu.upeu.lms.entity.CategoriaCurso;

@Mapper(componentModel = "spring")
public interface CategoriaCursoMapper {

    CategoriaCursoResponse toResponse(CategoriaCurso categoriaCurso);

    @Mapping(target = "id", ignore = true)
    CategoriaCurso toEntity(CategoriaCursoRequest request);
}
