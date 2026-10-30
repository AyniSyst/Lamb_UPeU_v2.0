package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.CupoGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.CupoGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.CupoGrupo;
import org.springframework.stereotype.Component;

@Component
public class CupoGrupoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CupoGrupo toEntity(CupoGrupoRequest request) {
        return CupoGrupo.builder()
                .tipo(request.getTipo())
                .cantidad(request.getCantidad())
                .build();
    }

    public CupoGrupoResponse toResponse(CupoGrupo cupoGrupo) {
        return CupoGrupoResponse.builder()
                .id(cupoGrupo.getId())
                .idGrupo(cupoGrupo.getGrupo().getId())
                .codigoGrupo(cupoGrupo.getGrupo().getCodigo())
                .tipo(cupoGrupo.getTipo())
                .cantidad(cupoGrupo.getCantidad())
                .build();
    }
}
