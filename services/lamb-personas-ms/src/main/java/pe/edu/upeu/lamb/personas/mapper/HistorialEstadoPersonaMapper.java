package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.HistorialEstadoPersonaRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialEstadoPersonaResponse;
import pe.edu.upeu.lamb.personas.entity.HistorialEstadoPersona;

@Component
public class HistorialEstadoPersonaMapper {

    public HistorialEstadoPersona toEntity(HistorialEstadoPersonaRequest request) {
        return HistorialEstadoPersona.builder()
                .idPersona(request.getIdPersona())
                .estadoAnterior(request.getEstadoAnterior())
                .estadoNuevo(request.getEstadoNuevo())
                .observacion(request.getObservacion())
                .build();
    }

    public HistorialEstadoPersonaResponse toResponse(HistorialEstadoPersona entity) {
        return HistorialEstadoPersonaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .estadoAnterior(entity.getEstadoAnterior())
                .estadoNuevo(entity.getEstadoNuevo())
                .observacion(entity.getObservacion())
                .fechaCambio(entity.getFechaCambio())
                .build();
    }
}
