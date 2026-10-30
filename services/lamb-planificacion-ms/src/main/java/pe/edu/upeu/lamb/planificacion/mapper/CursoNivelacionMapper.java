package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CursoNivelacionRequest;
import pe.edu.upeu.lamb.planificacion.dto.CursoNivelacionResponse;
import pe.edu.upeu.lamb.planificacion.entity.CursoNivelacion;
import org.springframework.stereotype.Component;

@Component
public class CursoNivelacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CursoNivelacion toEntity(CursoNivelacionRequest request) {
        return CursoNivelacion.builder()
                .tipo(request.getTipo())
                .cupoMaximo(request.getCupoMaximo())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();
    }

    public CursoNivelacionResponse toResponse(CursoNivelacion cursoNivelacion) {
        return CursoNivelacionResponse.builder()
                .id(cursoNivelacion.getId())
                .idPeriodo(cursoNivelacion.getPeriodo().getId())
                .codigoPeriodo(cursoNivelacion.getPeriodo().getCodigo())
                .idCurso(cursoNivelacion.getCurso().getId())
                .codigoCurso(cursoNivelacion.getCurso().getCodigo())
                .tipo(cursoNivelacion.getTipo())
                .cupoMaximo(cursoNivelacion.getCupoMaximo())
                .fechaInicio(cursoNivelacion.getFechaInicio())
                .fechaFin(cursoNivelacion.getFechaFin())
                .build();
    }
}
