package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.VigenciaCohorteRequest;
import pe.edu.upeu.lamb.curriculo.dto.VigenciaCohorteResponse;
import pe.edu.upeu.lamb.curriculo.entity.VigenciaCohorte;
import org.springframework.stereotype.Component;

@Component
public class VigenciaCohorteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public VigenciaCohorte toEntity(VigenciaCohorteRequest request) {
        return VigenciaCohorte.builder()
                .cohorteInicio(request.getCohorteInicio().trim())
                .cohorteFin(request.getCohorteFin())
                .observacion(request.getObservacion())
                .build();
    }

    public VigenciaCohorteResponse toResponse(VigenciaCohorte vigenciaCohorte) {
        return VigenciaCohorteResponse.builder()
                .id(vigenciaCohorte.getId())
                .idPlan(vigenciaCohorte.getPlan().getId())
                .versionPlan(vigenciaCohorte.getPlan().getVersion())
                .cohorteInicio(vigenciaCohorte.getCohorteInicio())
                .cohorteFin(vigenciaCohorte.getCohorteFin())
                .observacion(vigenciaCohorte.getObservacion())
                .build();
    }
}
