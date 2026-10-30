package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.RelacionFamiliarRequest;
import pe.edu.upeu.lamb.personas.dto.RelacionFamiliarResponse;
import pe.edu.upeu.lamb.personas.entity.RelacionFamiliar;

@Component
public class RelacionFamiliarMapper {

    public RelacionFamiliar toEntity(RelacionFamiliarRequest request) {
        return RelacionFamiliar.builder()
                .idPersona(request.getIdPersona())
                .idPersonaRelacionada(request.getIdPersonaRelacionada())
                .idTipoParentesco(request.getIdTipoParentesco())
                .build();
    }

    public RelacionFamiliarResponse toResponse(RelacionFamiliar entity) {
        return RelacionFamiliarResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idPersonaRelacionada(entity.getIdPersonaRelacionada())
                .idTipoParentesco(entity.getIdTipoParentesco())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
