package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.NotaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.NotaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota toEntity(NotaRequest request) {
        return Nota.builder()
                .idPersona(request.getIdPersona())
                .idSeccion(request.getIdSeccion())
                .tipoEvaluacion(request.getTipoEvaluacion())
                .calificacion(request.getCalificacion())
                .periodo(request.getPeriodo())
                .build();
    }

    public NotaResponse toResponse(Nota nota) {
        return NotaResponse.builder()
                .id(nota.getId())
                .idPersona(nota.getIdPersona())
                .idSeccion(nota.getIdSeccion())
                .tipoEvaluacion(nota.getTipoEvaluacion())
                .calificacion(nota.getCalificacion())
                .periodo(nota.getPeriodo())
                .build();
    }
}
