package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.NivelAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.NivelAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.NivelAcademico;

@Component
public class NivelAcademicoMapper {

    public NivelAcademico toEntity(NivelAcademicoRequest request) {
        return NivelAcademico.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public NivelAcademicoResponse toResponse(NivelAcademico entity) {
        return NivelAcademicoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
