package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.EstadoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.EstadoPersona;

@Component
public class EstadoPersonaMapper {

    public EstadoPersona toEntity(EstadoPersonaRequest request) {
        return EstadoPersona.builder()
                .idPersona(request.getIdPersona())
                .estado(request.getEstado())
                .motivo(request.getMotivo())
                .build();
    }

    public EstadoPersonaResponse toResponse(EstadoPersona entity) {
        return EstadoPersonaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .estado(entity.getEstado())
                .motivo(entity.getMotivo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
