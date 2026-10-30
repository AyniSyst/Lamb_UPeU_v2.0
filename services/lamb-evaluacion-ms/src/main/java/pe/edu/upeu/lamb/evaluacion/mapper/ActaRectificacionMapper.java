package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.ActaRectificacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ActaRectificacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ActaRectificacion;
import org.springframework.stereotype.Component;

@Component
public class ActaRectificacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ActaRectificacion toEntity(ActaRectificacionRequest request) {
        return ActaRectificacion.builder()
                .idPersona(request.getIdPersona())
                .notaAnterior(request.getNotaAnterior())
                .notaNueva(request.getNotaNueva())
                .motivo(request.getMotivo().trim())
                .resolucion(request.getResolucion())
                .build();
    }

    public ActaRectificacionResponse toResponse(ActaRectificacion actaRectificacion) {
        return ActaRectificacionResponse.builder()
                .id(actaRectificacion.getId())
                .idActa(actaRectificacion.getActa().getId())
                .codigoCursoActa(actaRectificacion.getActa().getCodigoCurso())
                .idPersona(actaRectificacion.getIdPersona())
                .notaAnterior(actaRectificacion.getNotaAnterior())
                .notaNueva(actaRectificacion.getNotaNueva())
                .motivo(actaRectificacion.getMotivo())
                .resolucion(actaRectificacion.getResolucion())
                .idSolicitante(actaRectificacion.getIdSolicitante())
                .estado(actaRectificacion.getEstado())
                .observacionRespuesta(actaRectificacion.getObservacionRespuesta())
                .idRespondidoPor(actaRectificacion.getIdRespondidoPor())
                .fechaSolicitud(actaRectificacion.getFechaSolicitud())
                .fechaRespuesta(actaRectificacion.getFechaRespuesta())
                .build();
    }
}
