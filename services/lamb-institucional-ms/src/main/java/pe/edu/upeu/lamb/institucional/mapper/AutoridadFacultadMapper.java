package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.AutoridadFacultadRequest;
import pe.edu.upeu.lamb.institucional.dto.AutoridadFacultadResponse;
import pe.edu.upeu.lamb.institucional.entity.AutoridadFacultad;

@Component
public class AutoridadFacultadMapper {

    public AutoridadFacultad toEntity(AutoridadFacultadRequest request) {
        return AutoridadFacultad.builder()
                .idPersona(request.getIdPersona())
                .idFacultad(request.getIdFacultad())
                .cargo(request.getCargo())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();
    }

    public AutoridadFacultadResponse toResponse(AutoridadFacultad entity) {
        return AutoridadFacultadResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idFacultad(entity.getIdFacultad())
                .cargo(entity.getCargo())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .build();
    }
}
