package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.RegimenAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.RegimenAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.RegimenAcademico;

@Component
public class RegimenAcademicoMapper {

    public RegimenAcademico toEntity(RegimenAcademicoRequest request) {
        return RegimenAcademico.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public RegimenAcademicoResponse toResponse(RegimenAcademico entity) {
        return RegimenAcademicoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
