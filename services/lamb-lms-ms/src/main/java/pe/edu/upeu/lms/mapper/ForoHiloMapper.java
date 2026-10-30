package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.ForoHiloRequest;
import pe.edu.upeu.lms.dto.ForoHiloResponse;
import pe.edu.upeu.lms.entity.ForoHilo;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface ForoHiloMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    ForoHilo toEntity(ForoHiloRequest request);

    ForoHiloResponse toResponse(ForoHilo foroHilo);
}
