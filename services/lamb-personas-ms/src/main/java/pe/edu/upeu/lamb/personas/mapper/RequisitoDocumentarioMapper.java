package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.RequisitoDocumentarioRequest;
import pe.edu.upeu.lamb.personas.dto.RequisitoDocumentarioResponse;
import pe.edu.upeu.lamb.personas.entity.RequisitoDocumentario;

@Component
public class RequisitoDocumentarioMapper {

    public RequisitoDocumentario toEntity(RequisitoDocumentarioRequest request) {
        return RequisitoDocumentario.builder()
                .idTipoDocumento(request.getIdTipoDocumento())
                .descripcion(request.getDescripcion())
                .obligatorio(request.getObligatorio())
                .build();
    }

    public RequisitoDocumentarioResponse toResponse(RequisitoDocumentario entity) {
        return RequisitoDocumentarioResponse.builder()
                .id(entity.getId())
                .idTipoDocumento(entity.getIdTipoDocumento())
                .descripcion(entity.getDescripcion())
                .obligatorio(entity.getObligatorio())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
