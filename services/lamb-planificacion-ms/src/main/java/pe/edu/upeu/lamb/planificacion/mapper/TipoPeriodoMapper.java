package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.TipoPeriodoRequest;
import pe.edu.upeu.lamb.planificacion.dto.TipoPeriodoResponse;
import pe.edu.upeu.lamb.planificacion.entity.TipoPeriodo;
import org.springframework.stereotype.Component;

@Component
public class TipoPeriodoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public TipoPeriodo toEntity(TipoPeriodoRequest request) {
        return TipoPeriodo.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoPeriodoResponse toResponse(TipoPeriodo tipoPeriodo) {
        return TipoPeriodoResponse.builder()
                .id(tipoPeriodo.getId())
                .codigo(tipoPeriodo.getCodigo())
                .nombre(tipoPeriodo.getNombre())
                .descripcion(tipoPeriodo.getDescripcion())
                .build();
    }
}
