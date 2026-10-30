package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDocumentoIdentidad;

@Component
public class TipoDocumentoIdentidadMapper {

    public TipoDocumentoIdentidad toEntity(TipoDocumentoIdentidadRequest request) {
        return TipoDocumentoIdentidad.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoDocumentoIdentidadResponse toResponse(TipoDocumentoIdentidad entity) {
        return TipoDocumentoIdentidadResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
