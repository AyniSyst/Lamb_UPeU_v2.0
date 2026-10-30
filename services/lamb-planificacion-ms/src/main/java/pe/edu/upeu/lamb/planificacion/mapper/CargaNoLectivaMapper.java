package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CargaNoLectivaRequest;
import pe.edu.upeu.lamb.planificacion.dto.CargaNoLectivaResponse;
import pe.edu.upeu.lamb.planificacion.entity.CargaNoLectiva;
import org.springframework.stereotype.Component;

@Component
public class CargaNoLectivaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CargaNoLectiva toEntity(CargaNoLectivaRequest request) {
        return CargaNoLectiva.builder()
                .idDocente(request.getIdDocente())
                .tipo(request.getTipo())
                .descripcion(request.getDescripcion())
                .horasSemanales(request.getHorasSemanales())
                .build();
    }

    public CargaNoLectivaResponse toResponse(CargaNoLectiva cargaNoLectiva) {
        return CargaNoLectivaResponse.builder()
                .id(cargaNoLectiva.getId())
                .idDocente(cargaNoLectiva.getIdDocente())
                .idPeriodo(cargaNoLectiva.getPeriodo().getId())
                .codigoPeriodo(cargaNoLectiva.getPeriodo().getCodigo())
                .tipo(cargaNoLectiva.getTipo())
                .descripcion(cargaNoLectiva.getDescripcion())
                .horasSemanales(cargaNoLectiva.getHorasSemanales())
                .build();
    }
}
