package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.DiscapacidadCondicionRequest;
import pe.edu.upeu.lamb.personas.dto.DiscapacidadCondicionResponse;
import pe.edu.upeu.lamb.personas.entity.DiscapacidadCondicion;

@Component
public class DiscapacidadCondicionMapper {

    public DiscapacidadCondicion toEntity(DiscapacidadCondicionRequest request) {
        return DiscapacidadCondicion.builder()
                .idPersona(request.getIdPersona())
                .tipo(request.getTipo())
                .observacion(request.getObservacion())
                .build();
    }

    public DiscapacidadCondicionResponse toResponse(DiscapacidadCondicion entity) {
        return DiscapacidadCondicionResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .tipo(entity.getTipo())
                .observacion(entity.getObservacion())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
