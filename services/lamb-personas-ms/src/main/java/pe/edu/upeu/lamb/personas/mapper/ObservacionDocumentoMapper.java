package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ObservacionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.ObservacionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.ObservacionDocumento;

@Component
public class ObservacionDocumentoMapper {

    public ObservacionDocumento toEntity(ObservacionDocumentoRequest request) {
        return ObservacionDocumento.builder()
                .idDocumentoPersona(request.getIdDocumentoPersona())
                .observacion(request.getObservacion())
                .build();
    }

    public ObservacionDocumentoResponse toResponse(ObservacionDocumento entity) {
        return ObservacionDocumentoResponse.builder()
                .id(entity.getId())
                .idDocumentoPersona(entity.getIdDocumentoPersona())
                .observacion(entity.getObservacion())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
