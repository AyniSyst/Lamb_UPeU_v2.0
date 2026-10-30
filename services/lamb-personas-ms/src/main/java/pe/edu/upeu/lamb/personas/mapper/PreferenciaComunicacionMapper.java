package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.PreferenciaComunicacionRequest;
import pe.edu.upeu.lamb.personas.dto.PreferenciaComunicacionResponse;
import pe.edu.upeu.lamb.personas.entity.PreferenciaComunicacion;

@Component
public class PreferenciaComunicacionMapper {

    public PreferenciaComunicacion toEntity(PreferenciaComunicacionRequest request) {
        return PreferenciaComunicacion.builder()
                .idPersona(request.getIdPersona())
                .canal(request.getCanal())
                .horario(request.getHorario())
                .build();
    }

    public PreferenciaComunicacionResponse toResponse(PreferenciaComunicacion entity) {
        return PreferenciaComunicacionResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .canal(entity.getCanal())
                .horario(entity.getHorario())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
