package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.OfertaAcademicaRequest;
import pe.edu.upeu.lamb.planificacion.dto.OfertaAcademicaResponse;
import pe.edu.upeu.lamb.planificacion.entity.OfertaAcademica;
import org.springframework.stereotype.Component;

@Component
public class OfertaAcademicaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public OfertaAcademica toEntity(OfertaAcademicaRequest request) {
        return OfertaAcademica.builder()
                .idPrograma(request.getIdPrograma())
                .ciclo(request.getCiclo())
                .estado(request.getEstado())
                .build();
    }

    public OfertaAcademicaResponse toResponse(OfertaAcademica ofertaAcademica) {
        return OfertaAcademicaResponse.builder()
                .id(ofertaAcademica.getId())
                .idPeriodo(ofertaAcademica.getPeriodo().getId())
                .codigoPeriodo(ofertaAcademica.getPeriodo().getCodigo())
                .idPrograma(ofertaAcademica.getIdPrograma())
                .idCurso(ofertaAcademica.getCurso().getId())
                .codigoCurso(ofertaAcademica.getCurso().getCodigo())
                .ciclo(ofertaAcademica.getCiclo())
                .estado(ofertaAcademica.getEstado())
                .build();
    }
}
