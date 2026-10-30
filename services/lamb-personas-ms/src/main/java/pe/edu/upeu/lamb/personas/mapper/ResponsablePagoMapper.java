package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ResponsablePagoRequest;
import pe.edu.upeu.lamb.personas.dto.ResponsablePagoResponse;
import pe.edu.upeu.lamb.personas.entity.ResponsablePago;

@Component
public class ResponsablePagoMapper {

    public ResponsablePago toEntity(ResponsablePagoRequest request) {
        return ResponsablePago.builder()
                .idPersona(request.getIdPersona())
                .idPersonaResponsable(request.getIdPersonaResponsable())
                .build();
    }

    public ResponsablePagoResponse toResponse(ResponsablePago entity) {
        return ResponsablePagoResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idPersonaResponsable(entity.getIdPersonaResponsable())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
