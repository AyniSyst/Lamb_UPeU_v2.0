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
                .idGrupo(request.getIdGrupo())
                .calificacion(request.getCalificacion())
                .periodo(request.getPeriodo())
                .build();
    }

    public NotaResponse toResponse(Nota nota) {
        return NotaResponse.builder()
                .id(nota.getId())
                .idPersona(nota.getIdPersona())
                .idGrupo(nota.getIdGrupo())
                .idComponente(nota.getComponente().getId())
                .codigoComponente(nota.getComponente().getCodigo())
                .calificacion(nota.getCalificacion())
                .periodo(nota.getPeriodo())
                .build();
    }
}
