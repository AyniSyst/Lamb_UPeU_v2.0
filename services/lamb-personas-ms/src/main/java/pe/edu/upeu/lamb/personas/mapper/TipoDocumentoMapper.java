package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.TipoDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDocumento;

@Component
public class TipoDocumentoMapper {

    public TipoDocumento toEntity(TipoDocumentoRequest request) {
        return TipoDocumento.builder()
                .nombre(request.getNombre())
                .build();
    }

    public TipoDocumentoResponse toResponse(TipoDocumento entity) {
        return TipoDocumentoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
