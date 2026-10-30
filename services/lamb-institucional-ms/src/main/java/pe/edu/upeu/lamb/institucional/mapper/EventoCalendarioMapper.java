package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.EventoCalendarioRequest;
import pe.edu.upeu.lamb.institucional.dto.EventoCalendarioResponse;
import pe.edu.upeu.lamb.institucional.entity.EventoCalendario;

@Component
public class EventoCalendarioMapper {

    public EventoCalendario toEntity(EventoCalendarioRequest request) {
        return EventoCalendario.builder()
                .nombre(request.getNombre())
                .idCalendarioAcademico(request.getIdCalendarioAcademico())
                .idTipoEvento(request.getIdTipoEvento())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();
    }

    public EventoCalendarioResponse toResponse(EventoCalendario entity) {
        return EventoCalendarioResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idCalendarioAcademico(entity.getIdCalendarioAcademico())
                .idTipoEvento(entity.getIdTipoEvento())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .build();
    }
}
