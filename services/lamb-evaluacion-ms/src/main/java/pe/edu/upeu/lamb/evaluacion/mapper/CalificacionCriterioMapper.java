package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.CalificacionCriterioRequest;
import pe.edu.upeu.lamb.evaluacion.dto.CalificacionCriterioResponse;
import pe.edu.upeu.lamb.evaluacion.entity.CalificacionCriterio;
import org.springframework.stereotype.Component;

@Component
public class CalificacionCriterioMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CalificacionCriterio toEntity(CalificacionCriterioRequest request) {
        return CalificacionCriterio.builder()
                .idGrupo(request.getIdGrupo())
                .idPersona(request.getIdPersona())
                .puntaje(request.getPuntaje())
                .build();
    }

    public CalificacionCriterioResponse toResponse(CalificacionCriterio calificacionCriterio) {
        return CalificacionCriterioResponse.builder()
                .id(calificacionCriterio.getId())
                .idCriterio(calificacionCriterio.getCriterio().getId())
                .idGrupo(calificacionCriterio.getIdGrupo())
                .idPersona(calificacionCriterio.getIdPersona())
                .puntaje(calificacionCriterio.getPuntaje())
                .build();
    }
}
