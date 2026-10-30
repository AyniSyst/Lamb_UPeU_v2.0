package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.PreferenciaCursoDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.PreferenciaCursoDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.PreferenciaCursoDocente;
import org.springframework.stereotype.Component;

@Component
public class PreferenciaCursoDocenteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PreferenciaCursoDocente toEntity(PreferenciaCursoDocenteRequest request) {
        return PreferenciaCursoDocente.builder()
                .idDocente(request.getIdDocente())
                .prioridad(request.getPrioridad())
                .build();
    }

    public PreferenciaCursoDocenteResponse toResponse(PreferenciaCursoDocente preferenciaCursoDocente) {
        return PreferenciaCursoDocenteResponse.builder()
                .id(preferenciaCursoDocente.getId())
                .idDocente(preferenciaCursoDocente.getIdDocente())
                .idPeriodo(preferenciaCursoDocente.getPeriodo().getId())
                .codigoPeriodo(preferenciaCursoDocente.getPeriodo().getCodigo())
                .idCurso(preferenciaCursoDocente.getCurso().getId())
                .codigoCurso(preferenciaCursoDocente.getCurso().getCodigo())
                .prioridad(preferenciaCursoDocente.getPrioridad())
                .build();
    }
}
