package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.UnidadAdministrativaRequest;
import pe.edu.upeu.lamb.institucional.dto.UnidadAdministrativaResponse;
import pe.edu.upeu.lamb.institucional.entity.UnidadAdministrativa;

@Component
public class UnidadAdministrativaMapper {

    public UnidadAdministrativa toEntity(UnidadAdministrativaRequest request) {
        return UnidadAdministrativa.builder()
                .nombre(request.getNombre())
                .idFacultad(request.getIdFacultad())
                .responsable(request.getResponsable())
                .descripcion(request.getDescripcion())
                .build();
    }

    public UnidadAdministrativaResponse toResponse(UnidadAdministrativa entity) {
        return UnidadAdministrativaResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idFacultad(entity.getIdFacultad())
                .responsable(entity.getResponsable())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
