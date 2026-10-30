package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ReemplazoVersionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.ReemplazoVersionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.ReemplazoVersionDocumento;

@Component
public class ReemplazoVersionDocumentoMapper {

    public ReemplazoVersionDocumento toEntity(ReemplazoVersionDocumentoRequest request) {
        return ReemplazoVersionDocumento.builder()
                .idDocumentoPersona(request.getIdDocumentoPersona())
                .idDocumentoPersonaAnterior(request.getIdDocumentoPersonaAnterior())
                .motivo(request.getMotivo())
                .build();
    }

    public ReemplazoVersionDocumentoResponse toResponse(ReemplazoVersionDocumento entity) {
        return ReemplazoVersionDocumentoResponse.builder()
                .id(entity.getId())
                .idDocumentoPersona(entity.getIdDocumentoPersona())
                .idDocumentoPersonaAnterior(entity.getIdDocumentoPersonaAnterior())
                .motivo(entity.getMotivo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
