package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.AutoridadInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.AutoridadInstitucional;

@Component
public class AutoridadInstitucionalMapper {

    public AutoridadInstitucional toEntity(AutoridadInstitucionalRequest request) {
        return AutoridadInstitucional.builder()
                .idPersona(request.getIdPersona())
                .cargo(request.getCargo())
                .idInstitucion(request.getIdInstitucion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();
    }

    public AutoridadInstitucionalResponse toResponse(AutoridadInstitucional entity) {
        return AutoridadInstitucionalResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .cargo(entity.getCargo())
                .idInstitucion(entity.getIdInstitucion())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .build();
    }
}
