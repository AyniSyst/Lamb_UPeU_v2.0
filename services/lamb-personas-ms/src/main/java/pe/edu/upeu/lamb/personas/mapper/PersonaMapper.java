package pe.edu.upeu.lamb.personas.mapper;

import pe.edu.upeu.lamb.personas.dto.PersonaRequest;
import pe.edu.upeu.lamb.personas.dto.PersonaResponse;
import pe.edu.upeu.lamb.personas.entity.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public Persona toEntity(PersonaRequest request) {
        return Persona.builder()
                .tipoDocumento(request.getTipoDocumento())
                .numeroDocumento(request.getNumeroDocumento())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .fechaNacimiento(request.getFechaNacimiento())
                .tipo(request.getTipo())
                .build();
    }

    public PersonaResponse toResponse(Persona persona) {
        return PersonaResponse.builder()
                .id(persona.getId())
                .tipoDocumento(persona.getTipoDocumento())
                .numeroDocumento(persona.getNumeroDocumento())
                .nombres(persona.getNombres())
                .apellidos(persona.getApellidos())
                .email(persona.getEmail())
                .telefono(persona.getTelefono())
                .fechaNacimiento(persona.getFechaNacimiento())
                .tipo(persona.getTipo())
                .build();
    }
}
