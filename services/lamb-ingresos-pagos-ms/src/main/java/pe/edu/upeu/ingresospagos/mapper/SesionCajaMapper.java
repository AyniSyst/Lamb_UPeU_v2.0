package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.SesionCajaRequest;
import pe.edu.upeu.ingresospagos.dto.SesionCajaResponse;
import pe.edu.upeu.ingresospagos.entity.SesionCaja;

@Mapper(componentModel = "spring", uses = CajaMapper.class)
public interface SesionCajaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "caja", ignore = true)
    @Mapping(target = "estado", ignore = true)
    SesionCaja toEntity(SesionCajaRequest request);

    SesionCajaResponse toResponse(SesionCaja sesionCaja);
}
