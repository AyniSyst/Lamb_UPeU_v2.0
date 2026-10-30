package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.BajaAnonimizacionRequest;
import pe.edu.upeu.lamb.personas.dto.BajaAnonimizacionResponse;
import pe.edu.upeu.lamb.personas.entity.BajaAnonimizacion;

@Component
public class BajaAnonimizacionMapper {

    public BajaAnonimizacion toEntity(BajaAnonimizacionRequest request) {
        return BajaAnonimizacion.builder()
                .idPersona(request.getIdPersona())
                .tipo(request.getTipo())
                .motivo(request.getMotivo())
                .build();
    }

    public BajaAnonimizacionResponse toResponse(BajaAnonimizacion entity) {
        return BajaAnonimizacionResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .tipo(entity.getTipo())
                .motivo(entity.getMotivo())
                .fechaAplicacion(entity.getFechaAplicacion())
                .build();
    }
}
