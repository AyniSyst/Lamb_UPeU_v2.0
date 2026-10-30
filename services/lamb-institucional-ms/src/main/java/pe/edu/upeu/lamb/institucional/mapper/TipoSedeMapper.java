package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.TipoSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoSede;

@Component
public class TipoSedeMapper {

    public TipoSede toEntity(TipoSedeRequest request) {
        return TipoSede.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoSedeResponse toResponse(TipoSede entity) {
        return TipoSedeResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
