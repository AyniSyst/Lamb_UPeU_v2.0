package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ExpedienteLegajoRequest;
import pe.edu.upeu.lamb.personas.dto.ExpedienteLegajoResponse;
import pe.edu.upeu.lamb.personas.entity.ExpedienteLegajo;

@Component
public class ExpedienteLegajoMapper {

    public ExpedienteLegajo toEntity(ExpedienteLegajoRequest request) {
        return ExpedienteLegajo.builder()
                .idPersona(request.getIdPersona())
                .codigo(request.getCodigo())
                .build();
    }

    public ExpedienteLegajoResponse toResponse(ExpedienteLegajo entity) {
        return ExpedienteLegajoResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .codigo(entity.getCodigo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
