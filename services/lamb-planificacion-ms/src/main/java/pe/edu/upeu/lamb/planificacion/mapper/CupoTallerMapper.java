package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CupoTallerRequest;
import pe.edu.upeu.lamb.planificacion.dto.CupoTallerResponse;
import pe.edu.upeu.lamb.planificacion.entity.CupoTaller;
import org.springframework.stereotype.Component;

@Component
public class CupoTallerMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CupoTaller toEntity(CupoTallerRequest request) {
        return CupoTaller.builder()
                .idPrograma(request.getIdPrograma())
                .cantidad(request.getCantidad())
                .build();
    }

    public CupoTallerResponse toResponse(CupoTaller cupoTaller) {
        return CupoTallerResponse.builder()
                .id(cupoTaller.getId())
                .idGrupoTaller(cupoTaller.getGrupoTaller().getId())
                .codigoGrupoTaller(cupoTaller.getGrupoTaller().getCodigo())
                .idPrograma(cupoTaller.getIdPrograma())
                .cantidad(cupoTaller.getCantidad())
                .build();
    }
}
