package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.LineaInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.LineaInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.LineaInvestigacion;
import org.springframework.stereotype.Component;

@Component
public class LineaInvestigacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public LineaInvestigacion toEntity(LineaInvestigacionRequest request) {
        return LineaInvestigacion.builder()
                .idPrograma(request.getIdPrograma())
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .activa(request.getActiva())
                .build();
    }

    public LineaInvestigacionResponse toResponse(LineaInvestigacion lineaInvestigacion) {
        return LineaInvestigacionResponse.builder()
                .id(lineaInvestigacion.getId())
                .idPrograma(lineaInvestigacion.getIdPrograma())
                .codigo(lineaInvestigacion.getCodigo())
                .nombre(lineaInvestigacion.getNombre())
                .descripcion(lineaInvestigacion.getDescripcion())
                .activa(lineaInvestigacion.getActiva())
                .build();
    }
}
