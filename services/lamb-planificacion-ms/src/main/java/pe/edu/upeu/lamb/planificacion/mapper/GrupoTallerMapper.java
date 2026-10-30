package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.GrupoTallerRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoTallerResponse;
import pe.edu.upeu.lamb.planificacion.entity.GrupoTaller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GrupoTallerMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public GrupoTaller toEntity(GrupoTallerRequest request) {
        return GrupoTaller.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .cupoMaximo(request.getCupoMaximo())
                .idProgramas(new ArrayList<>(request.getIdProgramas()))
                .build();
    }

    public GrupoTallerResponse toResponse(GrupoTaller grupoTaller) {
        return GrupoTallerResponse.builder()
                .id(grupoTaller.getId())
                .idPeriodo(grupoTaller.getPeriodo().getId())
                .codigoPeriodo(grupoTaller.getPeriodo().getCodigo())
                .idCurso(grupoTaller.getCurso().getId())
                .codigoCurso(grupoTaller.getCurso().getCodigo())
                .codigo(grupoTaller.getCodigo())
                .nombre(grupoTaller.getNombre())
                .cupoMaximo(grupoTaller.getCupoMaximo())
                .idProgramas(List.copyOf(grupoTaller.getIdProgramas()))
                .build();
    }
}
