package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.TipoEventoRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoEventoResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoEvento;

@Component
public class TipoEventoMapper {

    public TipoEvento toEntity(TipoEventoRequest request) {
        return TipoEvento.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoEventoResponse toResponse(TipoEvento entity) {
        return TipoEventoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
