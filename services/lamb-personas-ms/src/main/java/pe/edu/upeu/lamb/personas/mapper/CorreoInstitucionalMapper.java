package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.CorreoInstitucionalRequest;
import pe.edu.upeu.lamb.personas.dto.CorreoInstitucionalResponse;
import pe.edu.upeu.lamb.personas.entity.CorreoInstitucional;

@Component
public class CorreoInstitucionalMapper {

    public CorreoInstitucional toEntity(CorreoInstitucionalRequest request) {
        return CorreoInstitucional.builder()
                .idPersona(request.getIdPersona())
                .correo(request.getCorreo())
                .build();
    }

    public CorreoInstitucionalResponse toResponse(CorreoInstitucional entity) {
        return CorreoInstitucionalResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .correo(entity.getCorreo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
