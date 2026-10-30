package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.AsignacionDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.AsignacionDocente;
import org.springframework.stereotype.Component;

@Component
public class AsignacionDocenteMapper {

    public AsignacionDocenteResponse toResponse(AsignacionDocente asignacion) {
        return AsignacionDocenteResponse.builder()
                .id(asignacion.getId())
                .idGrupo(asignacion.getGrupo().getId())
                .codigoGrupo(asignacion.getGrupo().getCodigo())
                .idDocente(asignacion.getIdDocente())
                .rol(asignacion.getRol())
                .horasSemanales(asignacion.getHorasSemanales())
                .build();
    }
}
