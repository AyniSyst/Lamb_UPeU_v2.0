package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lms.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lms.entity.PeriodoAcademico;

@Mapper(componentModel = "spring")
public interface PeriodoAcademicoMapper {

    PeriodoAcademicoResponse toResponse(PeriodoAcademico periodoAcademico);

    @Mapping(target = "id", ignore = true)
    PeriodoAcademico toEntity(PeriodoAcademicoRequest request);
}
