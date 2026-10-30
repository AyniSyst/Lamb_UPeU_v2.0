package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoInvestigacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoInvestigacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoInvestigacion;
import org.springframework.stereotype.Component;

@Component
public class RequisitoInvestigacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RequisitoInvestigacion toEntity(RequisitoInvestigacionRequest request) {
        return RequisitoInvestigacion.builder()
                .tipoTrabajo(request.getTipoTrabajo().trim())
                .creditos(request.getCreditos())
                .descripcion(request.getDescripcion())
                .build();
    }

    public RequisitoInvestigacionResponse toResponse(RequisitoInvestigacion requisitoInvestigacion) {
        return RequisitoInvestigacionResponse.builder()
                .id(requisitoInvestigacion.getId())
                .idPlan(requisitoInvestigacion.getPlan().getId())
                .versionPlan(requisitoInvestigacion.getPlan().getVersion())
                .tipoTrabajo(requisitoInvestigacion.getTipoTrabajo())
                .creditos(requisitoInvestigacion.getCreditos())
                .descripcion(requisitoInvestigacion.getDescripcion())
                .build();
    }
}
