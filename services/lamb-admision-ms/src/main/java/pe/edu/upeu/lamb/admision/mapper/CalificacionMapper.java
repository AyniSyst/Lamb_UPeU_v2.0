package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.CalificacionResponse;
import pe.edu.upeu.lamb.admision.entity.CalificacionExamen;

@Component
public class CalificacionMapper {
    public CalificacionResponse toResponse(CalificacionExamen c) {
        return CalificacionResponse.builder()
                .id(c.getId())
                .idExamen(c.getExamen().getId())
                .codigoExamen(c.getExamen().getCodigo())
                .idPostulante(c.getPostulante().getId())
                .codigoPostulante(c.getPostulante().getCodigoPostulante())
                .puntajeTotal(c.getPuntajeTotal())
                .puntajeAptitud(c.getPuntajeAptitud())
                .puntajeConocimiento(c.getPuntajeConocimiento())
                .respuestasCorrectas(c.getRespuestasCorrectas())
                .respuestasIncorrectas(c.getRespuestasIncorrectas())
                .respuestasBlanco(c.getRespuestasBlanco())
                .ordenMerito(c.getOrdenMerito())
                .idSupervisor(c.getIdSupervisor())
                .idEncargado(c.getIdEncargado())
                .observacion(c.getObservacion())
                .fechaCalificacion(c.getFechaCalificacion())
                .build();
    }
}
