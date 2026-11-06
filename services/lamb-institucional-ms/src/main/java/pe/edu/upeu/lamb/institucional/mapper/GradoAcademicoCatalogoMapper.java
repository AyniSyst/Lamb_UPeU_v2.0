package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.GradoAcademicoCatalogoRequest;
import pe.edu.upeu.lamb.institucional.dto.GradoAcademicoCatalogoResponse;
import pe.edu.upeu.lamb.institucional.entity.GradoAcademicoCatalogo;

@Component
public class GradoAcademicoCatalogoMapper {

    public GradoAcademicoCatalogo toEntity(GradoAcademicoCatalogoRequest request) {
        return GradoAcademicoCatalogo.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public GradoAcademicoCatalogoResponse toResponse(GradoAcademicoCatalogo entity) {
        return GradoAcademicoCatalogoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
