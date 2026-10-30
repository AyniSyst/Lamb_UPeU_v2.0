package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.DocumentoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.DocumentoPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.DocumentoPersona;

@Component
public class DocumentoPersonaMapper {

    public DocumentoPersona toEntity(DocumentoPersonaRequest request) {
        return DocumentoPersona.builder()
                .idPersona(request.getIdPersona())
                .idTipoDocumento(request.getIdTipoDocumento())
                .urlArchivo(request.getUrlArchivo())
                .build();
    }

    public DocumentoPersonaResponse toResponse(DocumentoPersona entity) {
        return DocumentoPersonaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idTipoDocumento(entity.getIdTipoDocumento())
                .urlArchivo(entity.getUrlArchivo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
