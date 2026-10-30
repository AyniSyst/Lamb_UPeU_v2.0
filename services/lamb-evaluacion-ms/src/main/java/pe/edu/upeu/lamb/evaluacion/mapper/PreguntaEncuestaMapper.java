package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.PreguntaEncuestaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.PreguntaEncuestaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.PreguntaEncuesta;
import org.springframework.stereotype.Component;

@Component
public class PreguntaEncuestaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PreguntaEncuesta toEntity(PreguntaEncuestaRequest request) {
        return PreguntaEncuesta.builder()
                .orden(request.getOrden())
                .enunciado(request.getEnunciado().trim())
                .tipo(request.getTipo())
                .build();
    }

    public PreguntaEncuestaResponse toResponse(PreguntaEncuesta preguntaEncuesta) {
        return PreguntaEncuestaResponse.builder()
                .id(preguntaEncuesta.getId())
                .idEncuesta(preguntaEncuesta.getEncuesta().getId())
                .nombreEncuesta(preguntaEncuesta.getEncuesta().getNombre())
                .orden(preguntaEncuesta.getOrden())
                .enunciado(preguntaEncuesta.getEnunciado())
                .tipo(preguntaEncuesta.getTipo())
                .build();
    }
}
