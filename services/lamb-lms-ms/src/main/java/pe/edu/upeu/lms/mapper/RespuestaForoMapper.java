package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.RespuestaForoRequest;
import pe.edu.upeu.lms.dto.RespuestaForoResponse;
import pe.edu.upeu.lms.entity.RespuestaForo;

@Mapper(componentModel = "spring", uses = ForoHiloMapper.class)
public interface RespuestaForoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "foroHilo", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    RespuestaForo toEntity(RespuestaForoRequest request);

    RespuestaForoResponse toResponse(RespuestaForo respuestaForo);
}
