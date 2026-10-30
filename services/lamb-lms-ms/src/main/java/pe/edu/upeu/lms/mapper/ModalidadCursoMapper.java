package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.ModalidadCursoRequest;
import pe.edu.upeu.lms.dto.ModalidadCursoResponse;
import pe.edu.upeu.lms.entity.ModalidadCurso;

@Mapper(componentModel = "spring")
public interface ModalidadCursoMapper {

    ModalidadCursoResponse toResponse(ModalidadCurso modalidadCurso);

    @Mapping(target = "id", ignore = true)
    ModalidadCurso toEntity(ModalidadCursoRequest request);
}
