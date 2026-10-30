package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.DocumentoIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.DocumentoIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.DocumentoIdentidad;

@Component
public class DocumentoIdentidadMapper {

    public DocumentoIdentidad toEntity(DocumentoIdentidadRequest request) {
        return DocumentoIdentidad.builder()
                .idPersona(request.getIdPersona())
                .idTipoDocumentoIdentidad(request.getIdTipoDocumentoIdentidad())
                .numero(request.getNumero())
                .build();
    }

    public DocumentoIdentidadResponse toResponse(DocumentoIdentidad entity) {
        return DocumentoIdentidadResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idTipoDocumentoIdentidad(entity.getIdTipoDocumentoIdentidad())
                .numero(entity.getNumero())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
