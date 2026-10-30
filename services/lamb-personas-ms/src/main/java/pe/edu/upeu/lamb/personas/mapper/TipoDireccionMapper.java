package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.TipoDireccionRequest;
import pe.edu.upeu.lamb.personas.dto.TipoDireccionResponse;
import pe.edu.upeu.lamb.personas.entity.TipoDireccion;

@Component
public class TipoDireccionMapper {

    public TipoDireccion toEntity(TipoDireccionRequest request) {
        return TipoDireccion.builder()
                .nombre(request.getNombre())
                .build();
    }

    public TipoDireccionResponse toResponse(TipoDireccion entity) {
        return TipoDireccionResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
