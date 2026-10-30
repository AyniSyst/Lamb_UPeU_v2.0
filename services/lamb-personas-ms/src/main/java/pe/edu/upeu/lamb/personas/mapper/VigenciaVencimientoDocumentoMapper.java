package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.VigenciaVencimientoDocumentoRequest;
import pe.edu.upeu.lamb.personas.dto.VigenciaVencimientoDocumentoResponse;
import pe.edu.upeu.lamb.personas.entity.VigenciaVencimientoDocumento;

@Component
public class VigenciaVencimientoDocumentoMapper {

    public VigenciaVencimientoDocumento toEntity(VigenciaVencimientoDocumentoRequest request) {
        return VigenciaVencimientoDocumento.builder()
                .idDocumentoPersona(request.getIdDocumentoPersona())
                .fechaVigenciaDesde(request.getFechaVigenciaDesde())
                .fechaVencimiento(request.getFechaVencimiento())
                .build();
    }

    public VigenciaVencimientoDocumentoResponse toResponse(VigenciaVencimientoDocumento entity) {
        return VigenciaVencimientoDocumentoResponse.builder()
                .id(entity.getId())
                .idDocumentoPersona(entity.getIdDocumentoPersona())
                .fechaVigenciaDesde(entity.getFechaVigenciaDesde())
                .fechaVencimiento(entity.getFechaVencimiento())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
