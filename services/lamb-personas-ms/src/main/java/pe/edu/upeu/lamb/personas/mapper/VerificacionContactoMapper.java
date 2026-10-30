package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.VerificacionContactoRequest;
import pe.edu.upeu.lamb.personas.dto.VerificacionContactoResponse;
import pe.edu.upeu.lamb.personas.entity.VerificacionContacto;

@Component
public class VerificacionContactoMapper {

    public VerificacionContacto toEntity(VerificacionContactoRequest request) {
        return VerificacionContacto.builder()
                .idContacto(request.getIdContacto())
                .verificado(request.getVerificado())
                .build();
    }

    public VerificacionContactoResponse toResponse(VerificacionContacto entity) {
        return VerificacionContactoResponse.builder()
                .id(entity.getId())
                .idContacto(entity.getIdContacto())
                .verificado(entity.getVerificado())
                .fechaVerificacion(entity.getFechaVerificacion())
                .build();
    }
}
