package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.AnuncioCursoRequest;
import pe.edu.upeu.lms.dto.AnuncioCursoResponse;
import pe.edu.upeu.lms.entity.AnuncioCurso;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface AnuncioCursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "fechaPublicacion", ignore = true)
    AnuncioCurso toEntity(AnuncioCursoRequest request);

    AnuncioCursoResponse toResponse(AnuncioCurso anuncioCurso);
}
