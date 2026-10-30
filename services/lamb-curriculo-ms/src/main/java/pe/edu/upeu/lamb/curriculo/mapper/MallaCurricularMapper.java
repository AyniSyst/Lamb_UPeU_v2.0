package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.MallaCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.MallaCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.MallaCurricular;
import org.springframework.stereotype.Component;

@Component
public class MallaCurricularMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public MallaCurricular toEntity(MallaCurricularRequest request) {
        return MallaCurricular.builder()
                .nombreArchivo(request.getNombreArchivo().trim())
                .url(request.getUrl().trim())
                .version(request.getVersion())
                .fechaCarga(request.getFechaCarga())
                .build();
    }

    public MallaCurricularResponse toResponse(MallaCurricular mallaCurricular) {
        return MallaCurricularResponse.builder()
                .id(mallaCurricular.getId())
                .idPlan(mallaCurricular.getPlan().getId())
                .versionPlan(mallaCurricular.getPlan().getVersion())
                .nombreArchivo(mallaCurricular.getNombreArchivo())
                .url(mallaCurricular.getUrl())
                .version(mallaCurricular.getVersion())
                .fechaCarga(mallaCurricular.getFechaCarga())
                .build();
    }
}
