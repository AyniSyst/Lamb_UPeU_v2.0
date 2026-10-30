package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.TipoPeriodoRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoPeriodoResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoPeriodo;

@Component
public class TipoPeriodoMapper {

    public TipoPeriodo toEntity(TipoPeriodoRequest request) {
        return TipoPeriodo.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoPeriodoResponse toResponse(TipoPeriodo entity) {
        return TipoPeriodoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
