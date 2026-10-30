package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.MiembroComisionRequest;
import pe.edu.upeu.lamb.curriculo.dto.MiembroComisionResponse;
import pe.edu.upeu.lamb.curriculo.entity.MiembroComision;
import org.springframework.stereotype.Component;

@Component
public class MiembroComisionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public MiembroComision toEntity(MiembroComisionRequest request) {
        return MiembroComision.builder()
                .idPersona(request.getIdPersona())
                .rol(request.getRol())
                .fechaIncorporacion(request.getFechaIncorporacion())
                .build();
    }

    public MiembroComisionResponse toResponse(MiembroComision miembroComision) {
        return MiembroComisionResponse.builder()
                .id(miembroComision.getId())
                .idComision(miembroComision.getComision().getId())
                .nombreComision(miembroComision.getComision().getNombre())
                .idPersona(miembroComision.getIdPersona())
                .rol(miembroComision.getRol())
                .fechaIncorporacion(miembroComision.getFechaIncorporacion())
                .build();
    }
}
