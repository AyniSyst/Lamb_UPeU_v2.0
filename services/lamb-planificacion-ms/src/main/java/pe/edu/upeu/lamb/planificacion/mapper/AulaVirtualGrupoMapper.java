package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.AulaVirtualGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.AulaVirtualGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.AulaVirtualGrupo;
import org.springframework.stereotype.Component;

@Component
public class AulaVirtualGrupoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public AulaVirtualGrupo toEntity(AulaVirtualGrupoRequest request) {
        return AulaVirtualGrupo.builder()
                .plataforma(request.getPlataforma())
                .enlace(request.getEnlace().trim())
                .codigoAcceso(request.getCodigoAcceso())
                .build();
    }

    public AulaVirtualGrupoResponse toResponse(AulaVirtualGrupo aulaVirtualGrupo) {
        return AulaVirtualGrupoResponse.builder()
                .id(aulaVirtualGrupo.getId())
                .idGrupo(aulaVirtualGrupo.getGrupo().getId())
                .codigoGrupo(aulaVirtualGrupo.getGrupo().getCodigo())
                .plataforma(aulaVirtualGrupo.getPlataforma())
                .enlace(aulaVirtualGrupo.getEnlace())
                .codigoAcceso(aulaVirtualGrupo.getCodigoAcceso())
                .build();
    }
}
