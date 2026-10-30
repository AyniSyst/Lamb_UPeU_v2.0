package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.CajaRequest;
import pe.edu.upeu.ingresospagos.dto.CajaResponse;
import pe.edu.upeu.ingresospagos.entity.Caja;

@Mapper(componentModel = "spring")
public interface CajaMapper {

    CajaResponse toResponse(Caja caja);

    @Mapping(target = "id", ignore = true)
    Caja toEntity(CajaRequest request);
}
