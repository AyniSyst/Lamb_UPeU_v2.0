package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoRequest;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoResponse;
import pe.edu.upeu.ingresospagos.entity.FinanciamientoEducativo;

@Mapper(componentModel = "spring", uses = EntidadFinancieraMapper.class)
public interface FinanciamientoEducativoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entidadFinanciera", ignore = true)
    @Mapping(target = "estado", ignore = true)
    FinanciamientoEducativo toEntity(FinanciamientoEducativoRequest request);

    FinanciamientoEducativoResponse toResponse(FinanciamientoEducativo financiamientoEducativo);
}
