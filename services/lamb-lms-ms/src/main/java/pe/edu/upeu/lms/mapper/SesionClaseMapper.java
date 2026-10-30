package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.SesionClaseRequest;
import pe.edu.upeu.lms.dto.SesionClaseResponse;
import pe.edu.upeu.lms.entity.SesionClase;

@Mapper(componentModel = "spring", uses = SeccionCursoMapper.class)
public interface SesionClaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seccionCurso", ignore = true)
    SesionClase toEntity(SesionClaseRequest request);

    SesionClaseResponse toResponse(SesionClase sesionClase);
}
