package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.DocenteRequest;
import pe.edu.upeu.lms.dto.DocenteResponse;
import pe.edu.upeu.lms.entity.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    DocenteResponse toResponse(Docente docente);

    @Mapping(target = "id", ignore = true)
    Docente toEntity(DocenteRequest request);
}
