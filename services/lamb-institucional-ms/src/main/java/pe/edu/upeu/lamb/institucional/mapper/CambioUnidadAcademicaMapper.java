package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.CambioUnidadAcademicaRequest;
import pe.edu.upeu.lamb.institucional.dto.CambioUnidadAcademicaResponse;
import pe.edu.upeu.lamb.institucional.entity.CambioUnidadAcademica;

@Component
public class CambioUnidadAcademicaMapper {

    public CambioUnidadAcademica toEntity(CambioUnidadAcademicaRequest request) {
        return CambioUnidadAcademica.builder()
                .idFacultad(request.getIdFacultad())
                .idEscuela(request.getIdEscuela())
                .tipoCambio(request.getTipoCambio())
                .numeroResolucion(request.getNumeroResolucion())
                .fechaCambio(request.getFechaCambio())
                .descripcion(request.getDescripcion())
                .build();
    }

    public CambioUnidadAcademicaResponse toResponse(CambioUnidadAcademica entity) {
        return CambioUnidadAcademicaResponse.builder()
                .id(entity.getId())
                .idFacultad(entity.getIdFacultad())
                .idEscuela(entity.getIdEscuela())
                .tipoCambio(entity.getTipoCambio())
                .numeroResolucion(entity.getNumeroResolucion())
                .fechaCambio(entity.getFechaCambio())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
