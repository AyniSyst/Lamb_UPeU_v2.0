package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ReprogramacionEventoRequest;
import pe.edu.upeu.lamb.institucional.dto.ReprogramacionEventoResponse;
import pe.edu.upeu.lamb.institucional.entity.ReprogramacionEvento;

@Component
public class ReprogramacionEventoMapper {

    public ReprogramacionEvento toEntity(ReprogramacionEventoRequest request) {
        return ReprogramacionEvento.builder()
                .idEventoCalendario(request.getIdEventoCalendario())
                .numeroResolucion(request.getNumeroResolucion())
                .fechaInicioNueva(request.getFechaInicioNueva())
                .fechaFinNueva(request.getFechaFinNueva())
                .motivo(request.getMotivo())
                .build();
    }

    public ReprogramacionEventoResponse toResponse(ReprogramacionEvento entity) {
        return ReprogramacionEventoResponse.builder()
                .id(entity.getId())
                .idEventoCalendario(entity.getIdEventoCalendario())
                .numeroResolucion(entity.getNumeroResolucion())
                .fechaInicioNueva(entity.getFechaInicioNueva())
                .fechaFinNueva(entity.getFechaFinNueva())
                .motivo(entity.getMotivo())
                .build();
    }
}
