package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.GrupoHabilitadoEquivalenciaRequest;
import pe.edu.upeu.lamb.planificacion.dto.GrupoHabilitadoEquivalenciaResponse;
import pe.edu.upeu.lamb.planificacion.entity.GrupoHabilitadoEquivalencia;
import org.springframework.stereotype.Component;

@Component
public class GrupoHabilitadoEquivalenciaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public GrupoHabilitadoEquivalencia toEntity(GrupoHabilitadoEquivalenciaRequest request) {
        return GrupoHabilitadoEquivalencia.builder()
                .cuposHabilitados(request.getCuposHabilitados())
                .build();
    }

    public GrupoHabilitadoEquivalenciaResponse toResponse(GrupoHabilitadoEquivalencia grupoHabilitadoEquivalencia) {
        return GrupoHabilitadoEquivalenciaResponse.builder()
                .id(grupoHabilitadoEquivalencia.getId())
                .idEquivalencia(grupoHabilitadoEquivalencia.getEquivalencia().getId())
                .idGrupo(grupoHabilitadoEquivalencia.getGrupo().getId())
                .codigoGrupo(grupoHabilitadoEquivalencia.getGrupo().getCodigo())
                .cuposHabilitados(grupoHabilitadoEquivalencia.getCuposHabilitados())
                .cuposDisponibles(grupoHabilitadoEquivalencia.getCuposDisponibles())
                .build();
    }
}
