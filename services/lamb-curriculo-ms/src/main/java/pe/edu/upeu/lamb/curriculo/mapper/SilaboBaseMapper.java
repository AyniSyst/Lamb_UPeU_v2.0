package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.SilaboBaseRequest;
import pe.edu.upeu.lamb.curriculo.dto.SilaboBaseResponse;
import pe.edu.upeu.lamb.curriculo.entity.SilaboBase;
import org.springframework.stereotype.Component;

@Component
public class SilaboBaseMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SilaboBase toEntity(SilaboBaseRequest request) {
        return SilaboBase.builder()
                .version(request.getVersion().trim())
                .competencias(request.getCompetencias())
                .metodologia(request.getMetodologia())
                .evaluacion(request.getEvaluacion())
                .bibliografia(request.getBibliografia())
                .estado(request.getEstado())
                .build();
    }

    public SilaboBaseResponse toResponse(SilaboBase silaboBase) {
        return SilaboBaseResponse.builder()
                .id(silaboBase.getId())
                .idCurso(silaboBase.getCurso().getId())
                .codigoCurso(silaboBase.getCurso().getCodigo())
                .version(silaboBase.getVersion())
                .competencias(silaboBase.getCompetencias())
                .metodologia(silaboBase.getMetodologia())
                .evaluacion(silaboBase.getEvaluacion())
                .bibliografia(silaboBase.getBibliografia())
                .estado(silaboBase.getEstado())
                .build();
    }
}
