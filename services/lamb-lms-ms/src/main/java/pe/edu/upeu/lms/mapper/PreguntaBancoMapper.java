package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.PreguntaBancoRequest;
import pe.edu.upeu.lms.dto.PreguntaBancoResponse;
import pe.edu.upeu.lms.entity.PreguntaBanco;

@Mapper(componentModel = "spring")
public interface PreguntaBancoMapper {

    PreguntaBancoResponse toResponse(PreguntaBanco preguntaBanco);

    @Mapping(target = "id", ignore = true)
    PreguntaBanco toEntity(PreguntaBancoRequest request);
}
