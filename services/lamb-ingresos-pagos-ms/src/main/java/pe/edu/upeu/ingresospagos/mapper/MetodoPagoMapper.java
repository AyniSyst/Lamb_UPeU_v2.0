package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoResponse;
import pe.edu.upeu.ingresospagos.entity.MetodoPago;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {

    @Mapping(target = "id", ignore = true)
    MetodoPago toEntity(MetodoPagoRequest request);

    MetodoPagoResponse toResponse(MetodoPago metodoPago);
}
