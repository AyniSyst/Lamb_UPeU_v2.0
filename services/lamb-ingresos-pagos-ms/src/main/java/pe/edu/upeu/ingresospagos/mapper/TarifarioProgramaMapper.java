package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaRequest;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaResponse;
import pe.edu.upeu.ingresospagos.entity.TarifarioPrograma;

@Mapper(componentModel = "spring", uses = ConceptoPagoMapper.class)
public interface TarifarioProgramaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conceptoPago", ignore = true)
    TarifarioPrograma toEntity(TarifarioProgramaRequest request);

    TarifarioProgramaResponse toResponse(TarifarioPrograma tarifarioPrograma);
}
