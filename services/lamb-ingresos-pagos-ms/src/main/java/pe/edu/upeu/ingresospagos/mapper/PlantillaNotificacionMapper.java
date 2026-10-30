package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionRequest;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionResponse;
import pe.edu.upeu.ingresospagos.entity.PlantillaNotificacion;

@Mapper(componentModel = "spring")
public interface PlantillaNotificacionMapper {

    PlantillaNotificacionResponse toResponse(PlantillaNotificacion plantillaNotificacion);

    @Mapping(target = "id", ignore = true)
    PlantillaNotificacion toEntity(PlantillaNotificacionRequest request);
}
