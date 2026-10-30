package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.HorarioClaseRequest;
import pe.edu.upeu.lms.dto.HorarioClaseResponse;
import pe.edu.upeu.lms.entity.HorarioClase;

@Mapper(componentModel = "spring", uses = SeccionCursoMapper.class)
public interface HorarioClaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seccionCurso", ignore = true)
    HorarioClase toEntity(HorarioClaseRequest request);

    HorarioClaseResponse toResponse(HorarioClase horarioClase);
}
