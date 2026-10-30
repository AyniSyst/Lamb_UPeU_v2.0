package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.TipoContactoRequest;
import pe.edu.upeu.lamb.personas.dto.TipoContactoResponse;
import pe.edu.upeu.lamb.personas.entity.TipoContacto;

@Component
public class TipoContactoMapper {

    public TipoContacto toEntity(TipoContactoRequest request) {
        return TipoContacto.builder()
                .nombre(request.getNombre())
                .build();
    }

    public TipoContactoResponse toResponse(TipoContacto entity) {
        return TipoContactoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
