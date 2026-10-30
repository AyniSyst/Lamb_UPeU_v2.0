package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.FusionPersonasRequest;
import pe.edu.upeu.lamb.personas.dto.FusionPersonasResponse;
import pe.edu.upeu.lamb.personas.entity.FusionPersonas;

@Component
public class FusionPersonasMapper {

    public FusionPersonas toEntity(FusionPersonasRequest request) {
        return FusionPersonas.builder()
                .idPersonaOrigen(request.getIdPersonaOrigen())
                .idPersonaDestino(request.getIdPersonaDestino())
                .motivo(request.getMotivo())
                .build();
    }

    public FusionPersonasResponse toResponse(FusionPersonas entity) {
        return FusionPersonasResponse.builder()
                .id(entity.getId())
                .idPersonaOrigen(entity.getIdPersonaOrigen())
                .idPersonaDestino(entity.getIdPersonaDestino())
                .motivo(entity.getMotivo())
                .fechaFusion(entity.getFechaFusion())
                .build();
    }
}
