package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.GradoAcademicoRequest;
import pe.edu.upeu.lamb.curriculo.dto.GradoAcademicoResponse;
import pe.edu.upeu.lamb.curriculo.entity.GradoAcademico;
import org.springframework.stereotype.Component;

@Component
public class GradoAcademicoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public GradoAcademico toEntity(GradoAcademicoRequest request) {
        return GradoAcademico.builder()
                .idPrograma(request.getIdPrograma())
                .denominacion(request.getDenominacion().trim())
                .resolucion(request.getResolucion())
                .build();
    }

    public GradoAcademicoResponse toResponse(GradoAcademico gradoAcademico) {
        return GradoAcademicoResponse.builder()
                .id(gradoAcademico.getId())
                .idPrograma(gradoAcademico.getIdPrograma())
                .denominacion(gradoAcademico.getDenominacion())
                .resolucion(gradoAcademico.getResolucion())
                .build();
    }
}
