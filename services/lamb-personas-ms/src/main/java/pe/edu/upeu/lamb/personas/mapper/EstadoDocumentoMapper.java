package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.EstadoDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.EstadoDocumento;

@Component
public class EstadoDocumentoMapper {

    public EstadoDocumento toEntity(EstadoDocumentoRequest request) {
        return EstadoDocumento.builder()
                .idDocumentoPersona(request.getIdDocumentoPersona())
                .estado(request.getEstado())
                .build();
    }

    public EstadoDocumentoResponse toResponse(EstadoDocumento entity) {
        return EstadoDocumentoResponse.builder()
                .id(entity.getId())
                .idDocumentoPersona(entity.getIdDocumentoPersona())
                .estado(entity.getEstado())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
