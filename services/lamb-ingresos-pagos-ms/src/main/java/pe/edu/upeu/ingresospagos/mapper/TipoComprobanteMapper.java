package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteResponse;
import pe.edu.upeu.ingresospagos.entity.TipoComprobante;

@Mapper(componentModel = "spring")
public interface TipoComprobanteMapper {

    TipoComprobanteResponse toResponse(TipoComprobante tipoComprobante);

    @Mapping(target = "id", ignore = true)
    TipoComprobante toEntity(TipoComprobanteRequest request);
}
