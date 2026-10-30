package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.EquivalenciaCursoRequest;
import pe.edu.upeu.lamb.planificacion.dto.EquivalenciaCursoResponse;
import pe.edu.upeu.lamb.planificacion.entity.EquivalenciaCurso;
import org.springframework.stereotype.Component;

@Component
public class EquivalenciaCursoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EquivalenciaCurso toEntity(EquivalenciaCursoRequest request) {
        return EquivalenciaCurso.builder()
                .idProgramaOrigen(request.getIdProgramaOrigen())
                .idProgramaEquivalente(request.getIdProgramaEquivalente())
                .observacion(request.getObservacion())
                .build();
    }

    public EquivalenciaCursoResponse toResponse(EquivalenciaCurso equivalenciaCurso) {
        return EquivalenciaCursoResponse.builder()
                .id(equivalenciaCurso.getId())
                .idCurso(equivalenciaCurso.getCurso().getId())
                .codigoCurso(equivalenciaCurso.getCurso().getCodigo())
                .idProgramaOrigen(equivalenciaCurso.getIdProgramaOrigen())
                .idCursoEquivalente(equivalenciaCurso.getCursoEquivalente().getId())
                .codigoCursoEquivalente(equivalenciaCurso.getCursoEquivalente().getCodigo())
                .idProgramaEquivalente(equivalenciaCurso.getIdProgramaEquivalente())
                .observacion(equivalenciaCurso.getObservacion())
                .build();
    }
}
