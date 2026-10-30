package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.AnioAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.AnioAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.AnioAcademico;

@Component
public class AnioAcademicoMapper {

    public AnioAcademico toEntity(AnioAcademicoRequest request) {
        return AnioAcademico.builder()
                .anio(request.getAnio())
                .descripcion(request.getDescripcion())
                .build();
    }

    public AnioAcademicoResponse toResponse(AnioAcademico entity) {
        return AnioAcademicoResponse.builder()
                .id(entity.getId())
                .anio(entity.getAnio())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
