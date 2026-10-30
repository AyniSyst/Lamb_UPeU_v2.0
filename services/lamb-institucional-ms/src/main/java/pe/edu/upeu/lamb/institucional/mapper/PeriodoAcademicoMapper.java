package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.PeriodoAcademico;

@Component
public class PeriodoAcademicoMapper {

    public PeriodoAcademico toEntity(PeriodoAcademicoRequest request) {
        return PeriodoAcademico.builder()
                .nombre(request.getNombre())
                .idAnioAcademico(request.getIdAnioAcademico())
                .idTipoPeriodo(request.getIdTipoPeriodo())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();
    }

    public PeriodoAcademicoResponse toResponse(PeriodoAcademico entity) {
        return PeriodoAcademicoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idAnioAcademico(entity.getIdAnioAcademico())
                .idTipoPeriodo(entity.getIdTipoPeriodo())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .build();
    }
}
