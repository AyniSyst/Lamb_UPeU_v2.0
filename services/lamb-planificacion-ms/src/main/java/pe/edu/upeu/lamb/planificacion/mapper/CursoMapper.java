package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CursoResponse;
import pe.edu.upeu.lamb.planificacion.entity.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso) {
        return CursoResponse.builder()
                .id(curso.getId())
                .codigo(curso.getCodigo())
                .nombre(curso.getNombre())
                .creditos(curso.getCreditos())
                .build();
    }
}
