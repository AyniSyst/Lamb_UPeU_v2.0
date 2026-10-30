package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.EstadoMatriculaCatalogoRequest;
import pe.edu.upeu.lms.dto.EstadoMatriculaCatalogoResponse;
import pe.edu.upeu.lms.entity.EstadoMatriculaCatalogo;

@Mapper(componentModel = "spring")
public interface EstadoMatriculaCatalogoMapper {

    EstadoMatriculaCatalogoResponse toResponse(EstadoMatriculaCatalogo estadoMatriculaCatalogo);

    @Mapping(target = "id", ignore = true)
    EstadoMatriculaCatalogo toEntity(EstadoMatriculaCatalogoRequest request);
}
