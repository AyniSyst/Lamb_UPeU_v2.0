package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.SumillaCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.SumillaCursoResponse;
import pe.edu.upeu.lamb.curriculo.entity.SumillaCurso;
import org.springframework.stereotype.Component;

@Component
public class SumillaCursoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SumillaCurso toEntity(SumillaCursoRequest request) {
        return SumillaCurso.builder()
                .naturaleza(request.getNaturaleza().trim())
                .proposito(request.getProposito().trim())
                .contenido(request.getContenido().trim())
                .build();
    }

    public SumillaCursoResponse toResponse(SumillaCurso sumillaCurso) {
        return SumillaCursoResponse.builder()
                .id(sumillaCurso.getId())
                .idCurso(sumillaCurso.getCurso().getId())
                .codigoCurso(sumillaCurso.getCurso().getCodigo())
                .naturaleza(sumillaCurso.getNaturaleza())
                .proposito(sumillaCurso.getProposito())
                .contenido(sumillaCurso.getContenido())
                .build();
    }
}
