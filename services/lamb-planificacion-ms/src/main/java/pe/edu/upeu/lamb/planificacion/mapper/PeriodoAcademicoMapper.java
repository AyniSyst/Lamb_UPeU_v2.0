package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lamb.planificacion.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lamb.planificacion.entity.PeriodoAcademico;
import org.springframework.stereotype.Component;

@Component
public class PeriodoAcademicoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PeriodoAcademico toEntity(PeriodoAcademicoRequest request) {
        return PeriodoAcademico.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .estado(request.getEstado())
                .build();
    }

    public PeriodoAcademicoResponse toResponse(PeriodoAcademico periodoAcademico) {
        return PeriodoAcademicoResponse.builder()
                .id(periodoAcademico.getId())
                .codigo(periodoAcademico.getCodigo())
                .nombre(periodoAcademico.getNombre())
                .idTipoPeriodo(periodoAcademico.getTipoPeriodo().getId())
                .codigoTipoPeriodo(periodoAcademico.getTipoPeriodo().getCodigo())
                .fechaInicio(periodoAcademico.getFechaInicio())
                .fechaFin(periodoAcademico.getFechaFin())
                .estado(periodoAcademico.getEstado())
                .build();
    }
}
