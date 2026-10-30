package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.AutoidentificacionEtnicaRequest;
import pe.edu.upeu.lamb.personas.dto.AutoidentificacionEtnicaResponse;
import pe.edu.upeu.lamb.personas.entity.AutoidentificacionEtnica;

@Component
public class AutoidentificacionEtnicaMapper {

    public AutoidentificacionEtnica toEntity(AutoidentificacionEtnicaRequest request) {
        return AutoidentificacionEtnica.builder()
                .idPersona(request.getIdPersona())
                .etnia(request.getEtnia())
                .build();
    }

    public AutoidentificacionEtnicaResponse toResponse(AutoidentificacionEtnica entity) {
        return AutoidentificacionEtnicaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .etnia(entity.getEtnia())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
