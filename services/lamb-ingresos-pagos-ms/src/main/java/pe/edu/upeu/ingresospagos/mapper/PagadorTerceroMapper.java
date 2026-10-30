package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroRequest;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroResponse;
import pe.edu.upeu.ingresospagos.entity.PagadorTercero;

@Mapper(componentModel = "spring")
public interface PagadorTerceroMapper {

    PagadorTerceroResponse toResponse(PagadorTercero pagadorTercero);

    @Mapping(target = "id", ignore = true)
    PagadorTercero toEntity(PagadorTerceroRequest request);
}
