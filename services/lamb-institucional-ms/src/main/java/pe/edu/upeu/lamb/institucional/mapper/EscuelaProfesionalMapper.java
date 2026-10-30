package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.EscuelaProfesionalRequest;
import pe.edu.upeu.lamb.institucional.dto.EscuelaProfesionalResponse;
import pe.edu.upeu.lamb.institucional.entity.EscuelaProfesional;

@Component
public class EscuelaProfesionalMapper {

    public EscuelaProfesional toEntity(EscuelaProfesionalRequest request) {
        return EscuelaProfesional.builder()
                .nombre(request.getNombre())
                .idFacultad(request.getIdFacultad())
                .director(request.getDirector())
                .descripcion(request.getDescripcion())
                .build();
    }

    public EscuelaProfesionalResponse toResponse(EscuelaProfesional entity) {
        return EscuelaProfesionalResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idFacultad(entity.getIdFacultad())
                .director(entity.getDirector())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
