package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.HistorialRevisionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialRevisionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.HistorialRevisionDocumento;

@Component
public class HistorialRevisionDocumentoMapper {

    public HistorialRevisionDocumento toEntity(HistorialRevisionDocumentoRequest request) {
        return HistorialRevisionDocumento.builder()
                .idDocumentoPersona(request.getIdDocumentoPersona())
                .comentario(request.getComentario())
                .build();
    }

    public HistorialRevisionDocumentoResponse toResponse(HistorialRevisionDocumento entity) {
        return HistorialRevisionDocumentoResponse.builder()
                .id(entity.getId())
                .idDocumentoPersona(entity.getIdDocumentoPersona())
                .comentario(entity.getComentario())
                .fechaRevision(entity.getFechaRevision())
                .build();
    }
}
