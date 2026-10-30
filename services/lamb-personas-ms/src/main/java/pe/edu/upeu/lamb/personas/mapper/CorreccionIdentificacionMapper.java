package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.CorreccionIdentificacionRequest;
import pe.edu.upeu.lamb.personas.dto.CorreccionIdentificacionResponse;
import pe.edu.upeu.lamb.personas.entity.CorreccionIdentificacion;

@Component
public class CorreccionIdentificacionMapper {

    public CorreccionIdentificacion toEntity(CorreccionIdentificacionRequest request) {
        return CorreccionIdentificacion.builder()
                .idPersona(request.getIdPersona())
                .campoCorregido(request.getCampoCorregido())
                .valorAnterior(request.getValorAnterior())
                .valorNuevo(request.getValorNuevo())
                .sustento(request.getSustento())
                .build();
    }

    public CorreccionIdentificacionResponse toResponse(CorreccionIdentificacion entity) {
        return CorreccionIdentificacionResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .campoCorregido(entity.getCampoCorregido())
                .valorAnterior(entity.getValorAnterior())
                .valorNuevo(entity.getValorNuevo())
                .sustento(entity.getSustento())
                .fechaCorreccion(entity.getFechaCorreccion())
                .build();
    }
}
