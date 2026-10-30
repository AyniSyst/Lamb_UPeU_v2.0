package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CargaLectivaRequest;
import pe.edu.upeu.lamb.planificacion.dto.CargaLectivaResponse;
import pe.edu.upeu.lamb.planificacion.entity.CargaLectiva;
import org.springframework.stereotype.Component;

@Component
public class CargaLectivaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CargaLectiva toEntity(CargaLectivaRequest request) {
        return CargaLectiva.builder()
                .idDocente(request.getIdDocente())
                .horasMaximas(request.getHorasMaximas())
                .build();
    }

    public CargaLectivaResponse toResponse(CargaLectiva cargaLectiva) {
        return CargaLectivaResponse.builder()
                .id(cargaLectiva.getId())
                .idDocente(cargaLectiva.getIdDocente())
                .idPeriodo(cargaLectiva.getPeriodo().getId())
                .codigoPeriodo(cargaLectiva.getPeriodo().getCodigo())
                .horasMaximas(cargaLectiva.getHorasMaximas())
                .build();
    }
}
