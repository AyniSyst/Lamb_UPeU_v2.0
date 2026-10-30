package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CalendarioAcademicoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CalendarioAcademicoResponse;
import pe.edu.upeu.lamb.planificacion.entity.CalendarioAcademico;
import org.springframework.stereotype.Component;

@Component
public class CalendarioAcademicoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CalendarioAcademico toEntity(CalendarioAcademicoRequest request) {
        return CalendarioAcademico.builder()
                .idSede(request.getIdSede())
                .nombre(request.getNombre().trim())
                .resolucion(request.getResolucion())
                .estado(request.getEstado())
                .build();
    }

    public CalendarioAcademicoResponse toResponse(CalendarioAcademico calendarioAcademico) {
        return CalendarioAcademicoResponse.builder()
                .id(calendarioAcademico.getId())
                .idPeriodo(calendarioAcademico.getPeriodo().getId())
                .codigoPeriodo(calendarioAcademico.getPeriodo().getCodigo())
                .idSede(calendarioAcademico.getIdSede())
                .nombre(calendarioAcademico.getNombre())
                .resolucion(calendarioAcademico.getResolucion())
                .estado(calendarioAcademico.getEstado())
                .build();
    }
}
