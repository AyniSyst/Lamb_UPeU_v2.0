package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CursoInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.CursoInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.CursoInvestigacion;
import org.springframework.stereotype.Component;

@Component
public class CursoInvestigacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CursoInvestigacion toEntity(CursoInvestigacionRequest request) {
        return CursoInvestigacion.builder()
                .build();
    }

    public CursoInvestigacionResponse toResponse(CursoInvestigacion cursoInvestigacion) {
        return CursoInvestigacionResponse.builder()
                .id(cursoInvestigacion.getId())
                .idLinea(cursoInvestigacion.getLinea().getId())
                .codigoLinea(cursoInvestigacion.getLinea().getCodigo())
                .idPlanCurso(cursoInvestigacion.getPlanCurso().getId())
                .build();
    }
}
