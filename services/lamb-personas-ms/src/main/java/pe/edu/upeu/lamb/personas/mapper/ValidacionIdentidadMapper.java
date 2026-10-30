package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ValidacionIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.ValidacionIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.ValidacionIdentidad;

@Component
public class ValidacionIdentidadMapper {

    public ValidacionIdentidad toEntity(ValidacionIdentidadRequest request) {
        return ValidacionIdentidad.builder()
                .idPersona(request.getIdPersona())
                .fuente(request.getFuente())
                .resultado(request.getResultado())
                .observacion(request.getObservacion())
                .build();
    }

    public ValidacionIdentidadResponse toResponse(ValidacionIdentidad entity) {
        return ValidacionIdentidadResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .fuente(entity.getFuente())
                .resultado(entity.getResultado())
                .observacion(entity.getObservacion())
                .fechaValidacion(entity.getFechaValidacion())
                .build();
    }
}
