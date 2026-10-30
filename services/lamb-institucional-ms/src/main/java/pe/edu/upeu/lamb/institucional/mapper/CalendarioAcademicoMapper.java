package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.CalendarioAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.CalendarioAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.CalendarioAcademico;

@Component
public class CalendarioAcademicoMapper {

    public CalendarioAcademico toEntity(CalendarioAcademicoRequest request) {
        return CalendarioAcademico.builder()
                .nombre(request.getNombre())
                .idPeriodoAcademico(request.getIdPeriodoAcademico())
                .descripcion(request.getDescripcion())
                .build();
    }

    public CalendarioAcademicoResponse toResponse(CalendarioAcademico entity) {
        return CalendarioAcademicoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idPeriodoAcademico(entity.getIdPeriodoAcademico())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
