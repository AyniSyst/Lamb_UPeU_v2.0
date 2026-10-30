package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoResponse;
import pe.edu.upeu.ingresospagos.entity.ConceptoPago;

@Mapper(componentModel = "spring")
public interface ConceptoPagoMapper {

    @Mapping(target = "id", ignore = true)
    ConceptoPago toEntity(ConceptoPagoRequest request);

    ConceptoPagoResponse toResponse(ConceptoPago conceptoPago);
}
