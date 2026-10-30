package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ConfesionReligiosaRequest;
import pe.edu.upeu.lamb.personas.dto.ConfesionReligiosaResponse;
import pe.edu.upeu.lamb.personas.entity.ConfesionReligiosa;

@Component
public class ConfesionReligiosaMapper {

    public ConfesionReligiosa toEntity(ConfesionReligiosaRequest request) {
        return ConfesionReligiosa.builder()
                .idPersona(request.getIdPersona())
                .religion(request.getReligion())
                .build();
    }

    public ConfesionReligiosaResponse toResponse(ConfesionReligiosa entity) {
        return ConfesionReligiosaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .religion(entity.getReligion())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
