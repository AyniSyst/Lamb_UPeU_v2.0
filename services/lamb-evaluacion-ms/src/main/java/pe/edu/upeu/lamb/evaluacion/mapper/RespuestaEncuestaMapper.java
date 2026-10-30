package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.RespuestaEncuestaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaEncuestaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.RespuestaEncuesta;
import org.springframework.stereotype.Component;

@Component
public class RespuestaEncuestaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RespuestaEncuesta toEntity(RespuestaEncuestaRequest request) {
        return RespuestaEncuesta.builder()
                .idGrupo(request.getIdGrupo())
                .idDocente(request.getIdDocente())
                .valorEscala(request.getValorEscala())
                .valorTexto(request.getValorTexto())
                .build();
    }

    public RespuestaEncuestaResponse toResponse(RespuestaEncuesta respuestaEncuesta) {
        return RespuestaEncuestaResponse.builder()
                .id(respuestaEncuesta.getId())
                .idPregunta(respuestaEncuesta.getPregunta().getId())
                .idGrupo(respuestaEncuesta.getIdGrupo())
                .idDocente(respuestaEncuesta.getIdDocente())
                .valorEscala(respuestaEncuesta.getValorEscala())
                .valorTexto(respuestaEncuesta.getValorTexto())
                .build();
    }
}
