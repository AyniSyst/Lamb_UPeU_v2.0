package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.EncuestaDocenteRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EncuestaDocenteResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EncuestaDocente;
import org.springframework.stereotype.Component;

@Component
public class EncuestaDocenteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EncuestaDocente toEntity(EncuestaDocenteRequest request) {
        return EncuestaDocente.builder()
                .nombre(request.getNombre().trim())
                .idPeriodo(request.getIdPeriodo())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .estado(request.getEstado())
                .build();
    }

    public EncuestaDocenteResponse toResponse(EncuestaDocente encuestaDocente) {
        return EncuestaDocenteResponse.builder()
                .id(encuestaDocente.getId())
                .nombre(encuestaDocente.getNombre())
                .idPeriodo(encuestaDocente.getIdPeriodo())
                .fechaInicio(encuestaDocente.getFechaInicio())
                .fechaFin(encuestaDocente.getFechaFin())
                .estado(encuestaDocente.getEstado())
                .build();
    }
}
