package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.CodigoInstitucionalRequest;
import pe.edu.upeu.lamb.personas.dto.CodigoInstitucionalResponse;
import pe.edu.upeu.lamb.personas.entity.CodigoInstitucional;

@Component
public class CodigoInstitucionalMapper {

    public CodigoInstitucional toEntity(CodigoInstitucionalRequest request) {
        return CodigoInstitucional.builder()
                .idPersona(request.getIdPersona())
                .tipo(request.getTipo())
                .codigo(request.getCodigo())
                .build();
    }

    public CodigoInstitucionalResponse toResponse(CodigoInstitucional entity) {
        return CodigoInstitucionalResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .tipo(entity.getTipo())
                .codigo(entity.getCodigo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
