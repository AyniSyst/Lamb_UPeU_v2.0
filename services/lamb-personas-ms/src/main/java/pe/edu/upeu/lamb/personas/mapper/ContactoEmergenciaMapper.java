package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ContactoEmergenciaRequest;
import pe.edu.upeu.lamb.personas.dto.ContactoEmergenciaResponse;
import pe.edu.upeu.lamb.personas.entity.ContactoEmergencia;

@Component
public class ContactoEmergenciaMapper {

    public ContactoEmergencia toEntity(ContactoEmergenciaRequest request) {
        return ContactoEmergencia.builder()
                .idPersona(request.getIdPersona())
                .nombreContacto(request.getNombreContacto())
                .telefono(request.getTelefono())
                .parentesco(request.getParentesco())
                .build();
    }

    public ContactoEmergenciaResponse toResponse(ContactoEmergencia entity) {
        return ContactoEmergenciaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .nombreContacto(entity.getNombreContacto())
                .telefono(entity.getTelefono())
                .parentesco(entity.getParentesco())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
