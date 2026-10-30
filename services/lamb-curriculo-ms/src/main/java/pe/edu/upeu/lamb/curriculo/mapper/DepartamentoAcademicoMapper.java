package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.DepartamentoAcademicoRequest;
import pe.edu.upeu.lamb.curriculo.dto.DepartamentoAcademicoResponse;
import pe.edu.upeu.lamb.curriculo.entity.DepartamentoAcademico;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoAcademicoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public DepartamentoAcademico toEntity(DepartamentoAcademicoRequest request) {
        return DepartamentoAcademico.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .idFacultad(request.getIdFacultad())
                .build();
    }

    public DepartamentoAcademicoResponse toResponse(DepartamentoAcademico departamentoAcademico) {
        return DepartamentoAcademicoResponse.builder()
                .id(departamentoAcademico.getId())
                .idPlan(departamentoAcademico.getPlan().getId())
                .versionPlan(departamentoAcademico.getPlan().getVersion())
                .codigo(departamentoAcademico.getCodigo())
                .nombre(departamentoAcademico.getNombre())
                .idFacultad(departamentoAcademico.getIdFacultad())
                .build();
    }
}
