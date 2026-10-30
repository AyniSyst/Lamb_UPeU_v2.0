package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.AvanceContenidoRequest;
import pe.edu.upeu.lms.dto.AvanceContenidoResponse;
import pe.edu.upeu.lms.entity.AvanceContenido;

@Mapper(componentModel = "spring", uses = ContenidoMapper.class)
public interface AvanceContenidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contenido", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    AvanceContenido toEntity(AvanceContenidoRequest request);

    AvanceContenidoResponse toResponse(AvanceContenido avanceContenido);
}
