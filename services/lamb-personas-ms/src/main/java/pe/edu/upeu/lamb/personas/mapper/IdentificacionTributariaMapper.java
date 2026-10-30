package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.IdentificacionTributariaRequest;
import pe.edu.upeu.lamb.personas.dto.IdentificacionTributariaResponse;
import pe.edu.upeu.lamb.personas.entity.IdentificacionTributaria;

@Component
public class IdentificacionTributariaMapper {

    public IdentificacionTributaria toEntity(IdentificacionTributariaRequest request) {
        return IdentificacionTributaria.builder()
                .idPersona(request.getIdPersona())
                .ruc(request.getRuc())
                .razonSocial(request.getRazonSocial())
                .build();
    }

    public IdentificacionTributariaResponse toResponse(IdentificacionTributaria entity) {
        return IdentificacionTributariaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .ruc(entity.getRuc())
                .razonSocial(entity.getRazonSocial())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
