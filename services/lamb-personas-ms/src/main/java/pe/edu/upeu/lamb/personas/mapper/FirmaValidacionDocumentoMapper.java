package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.FirmaValidacionDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.FirmaValidacionDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.FirmaValidacionDocumento;

@Component
public class FirmaValidacionDocumentoMapper {

    public FirmaValidacionDocumento toEntity(FirmaValidacionDocumentoRequest request) {
        return FirmaValidacionDocumento.builder()
                .idDocumentoPersona(request.getIdDocumentoPersona())
                .firmante(request.getFirmante())
                .build();
    }

    public FirmaValidacionDocumentoResponse toResponse(FirmaValidacionDocumento entity) {
        return FirmaValidacionDocumentoResponse.builder()
                .id(entity.getId())
                .idDocumentoPersona(entity.getIdDocumentoPersona())
                .firmante(entity.getFirmante())
                .fechaFirma(entity.getFechaFirma())
                .build();
    }
}
