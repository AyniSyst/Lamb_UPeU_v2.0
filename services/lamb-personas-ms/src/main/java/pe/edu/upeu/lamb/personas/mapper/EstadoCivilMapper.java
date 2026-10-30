package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.EstadoCivilRequest;
import pe.edu.upeu.lamb.personas.dto.EstadoCivilResponse;
import pe.edu.upeu.lamb.personas.entity.EstadoCivil;

@Component
public class EstadoCivilMapper {

    public EstadoCivil toEntity(EstadoCivilRequest request) {
        return EstadoCivil.builder()
                .nombre(request.getNombre())
                .build();
    }

    public EstadoCivilResponse toResponse(EstadoCivil entity) {
        return EstadoCivilResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
