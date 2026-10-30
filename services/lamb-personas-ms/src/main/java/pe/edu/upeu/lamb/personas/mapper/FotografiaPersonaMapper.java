package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.FotografiaPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.FotografiaPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.FotografiaPersona;

@Component
public class FotografiaPersonaMapper {

    public FotografiaPersona toEntity(FotografiaPersonaRequest request) {
        return FotografiaPersona.builder()
                .idPersona(request.getIdPersona())
                .urlArchivo(request.getUrlArchivo())
                .build();
    }

    public FotografiaPersonaResponse toResponse(FotografiaPersona entity) {
        return FotografiaPersonaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .urlArchivo(entity.getUrlArchivo())
                .fechaCarga(entity.getFechaCarga())
                .build();
    }
}
