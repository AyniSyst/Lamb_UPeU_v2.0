package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ParametroInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.ParametroInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.ParametroInstitucional;

@Component
public class ParametroInstitucionalMapper {

    public ParametroInstitucional toEntity(ParametroInstitucionalRequest request) {
        return ParametroInstitucional.builder()
                .clave(request.getClave())
                .valor(request.getValor())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ParametroInstitucionalResponse toResponse(ParametroInstitucional entity) {
        return ParametroInstitucionalResponse.builder()
                .id(entity.getId())
                .clave(entity.getClave())
                .valor(entity.getValor())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
