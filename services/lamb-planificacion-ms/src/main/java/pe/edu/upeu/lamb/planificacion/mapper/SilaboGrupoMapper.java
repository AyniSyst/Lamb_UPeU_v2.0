package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SilaboGrupoRequest;
import pe.edu.upeu.lamb.planificacion.dto.SilaboGrupoResponse;
import pe.edu.upeu.lamb.planificacion.entity.SilaboGrupo;
import org.springframework.stereotype.Component;

@Component
public class SilaboGrupoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SilaboGrupo toEntity(SilaboGrupoRequest request) {
        return SilaboGrupo.builder()
                .idSilaboBase(request.getIdSilaboBase())
                .metodologia(request.getMetodologia())
                .estado(request.getEstado())
                .fechaPresentacion(request.getFechaPresentacion())
                .build();
    }

    public SilaboGrupoResponse toResponse(SilaboGrupo silaboGrupo) {
        return SilaboGrupoResponse.builder()
                .id(silaboGrupo.getId())
                .idGrupo(silaboGrupo.getGrupo().getId())
                .codigoGrupo(silaboGrupo.getGrupo().getCodigo())
                .idSilaboBase(silaboGrupo.getIdSilaboBase())
                .metodologia(silaboGrupo.getMetodologia())
                .estado(silaboGrupo.getEstado())
                .fechaPresentacion(silaboGrupo.getFechaPresentacion())
                .build();
    }
}
