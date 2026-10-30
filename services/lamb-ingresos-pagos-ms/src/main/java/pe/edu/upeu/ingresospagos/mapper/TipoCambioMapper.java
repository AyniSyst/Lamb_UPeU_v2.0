package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.TipoCambioRequest;
import pe.edu.upeu.ingresospagos.dto.TipoCambioResponse;
import pe.edu.upeu.ingresospagos.entity.TipoCambio;

@Mapper(componentModel = "spring")
public interface TipoCambioMapper {

    TipoCambioResponse toResponse(TipoCambio tipoCambio);

    @Mapping(target = "id", ignore = true)
    TipoCambio toEntity(TipoCambioRequest request);
}
