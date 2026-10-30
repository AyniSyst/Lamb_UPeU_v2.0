package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.BecaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaResponse;
import pe.edu.upeu.ingresospagos.entity.Beca;

@Mapper(componentModel = "spring")
public interface BecaMapper {

    @Mapping(target = "id", ignore = true)
    Beca toEntity(BecaRequest request);

    BecaResponse toResponse(Beca beca);
}
