package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaRequest;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaResponse;
import pe.edu.upeu.ingresospagos.entity.ArqueoCaja;

@Mapper(componentModel = "spring", uses = SesionCajaMapper.class)
public interface ArqueoCajaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sesionCaja", ignore = true)
    @Mapping(target = "diferencia", ignore = true)
    @Mapping(target = "fechaArqueo", ignore = true)
    ArqueoCaja toEntity(ArqueoCajaRequest request);

    ArqueoCajaResponse toResponse(ArqueoCaja arqueoCaja);
}
