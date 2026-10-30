package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.TipoCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.TipoCursoResponse;
import pe.edu.upeu.lamb.curriculo.entity.TipoCurso;
import org.springframework.stereotype.Component;

@Component
public class TipoCursoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public TipoCurso toEntity(TipoCursoRequest request) {
        return TipoCurso.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoCursoResponse toResponse(TipoCurso tipoCurso) {
        return TipoCursoResponse.builder()
                .id(tipoCurso.getId())
                .codigo(tipoCurso.getCodigo())
                .nombre(tipoCurso.getNombre())
                .descripcion(tipoCurso.getDescripcion())
                .build();
    }
}
