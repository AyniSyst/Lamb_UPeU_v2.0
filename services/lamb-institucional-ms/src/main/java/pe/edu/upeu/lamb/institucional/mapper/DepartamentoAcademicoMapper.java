package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.DepartamentoAcademicoRequest;
import pe.edu.upeu.lamb.institucional.dto.DepartamentoAcademicoResponse;
import pe.edu.upeu.lamb.institucional.entity.DepartamentoAcademico;

@Component
public class DepartamentoAcademicoMapper {

    public DepartamentoAcademico toEntity(DepartamentoAcademicoRequest request) {
        return DepartamentoAcademico.builder()
                .nombre(request.getNombre())
                .idFacultad(request.getIdFacultad())
                .jefeDepartamento(request.getJefeDepartamento())
                .descripcion(request.getDescripcion())
                .build();
    }

    public DepartamentoAcademicoResponse toResponse(DepartamentoAcademico entity) {
        return DepartamentoAcademicoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idFacultad(entity.getIdFacultad())
                .jefeDepartamento(entity.getJefeDepartamento())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
