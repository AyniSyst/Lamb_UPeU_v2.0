package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ContactoRequest;
import pe.edu.upeu.lamb.personas.dto.ContactoResponse;
import pe.edu.upeu.lamb.personas.entity.Contacto;

@Component
public class ContactoMapper {

    public Contacto toEntity(ContactoRequest request) {
        return Contacto.builder()
                .idPersona(request.getIdPersona())
                .idTipoContacto(request.getIdTipoContacto())
                .valor(request.getValor())
                .build();
    }

    public ContactoResponse toResponse(Contacto entity) {
        return ContactoResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idTipoContacto(entity.getIdTipoContacto())
                .valor(entity.getValor())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
