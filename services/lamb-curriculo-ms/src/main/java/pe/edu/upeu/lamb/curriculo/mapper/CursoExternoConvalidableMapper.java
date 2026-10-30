package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CursoExternoConvalidableRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoExternoConvalidableResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoExternoConvalidable;
import org.springframework.stereotype.Component;

@Component
public class CursoExternoConvalidableMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CursoExternoConvalidable toEntity(CursoExternoConvalidableRequest request) {
        return CursoExternoConvalidable.builder()
                .codigoExterno(request.getCodigoExterno().trim())
                .nombreExterno(request.getNombreExterno().trim())
                .creditosExterno(request.getCreditosExterno())
                .notaMinima(request.getNotaMinima())
                .build();
    }

    public CursoExternoConvalidableResponse toResponse(CursoExternoConvalidable cursoExternoConvalidable) {
        return CursoExternoConvalidableResponse.builder()
                .id(cursoExternoConvalidable.getId())
                .idTabla(cursoExternoConvalidable.getTabla().getId())
                .nombreTabla(cursoExternoConvalidable.getTabla().getNombre())
                .codigoExterno(cursoExternoConvalidable.getCodigoExterno())
                .nombreExterno(cursoExternoConvalidable.getNombreExterno())
                .creditosExterno(cursoExternoConvalidable.getCreditosExterno())
                .idPlanCurso(cursoExternoConvalidable.getPlanCurso().getId())
                .notaMinima(cursoExternoConvalidable.getNotaMinima())
                .build();
    }
}
