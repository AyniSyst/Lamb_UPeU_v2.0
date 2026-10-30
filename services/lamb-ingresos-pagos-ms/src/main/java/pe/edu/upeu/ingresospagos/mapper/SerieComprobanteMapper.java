package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteResponse;
import pe.edu.upeu.ingresospagos.entity.SerieComprobante;

@Mapper(componentModel = "spring", uses = TipoComprobanteMapper.class)
public interface SerieComprobanteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoComprobante", ignore = true)
    SerieComprobante toEntity(SerieComprobanteRequest request);

    SerieComprobanteResponse toResponse(SerieComprobante serieComprobante);
}
