package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.DisponibilidadDocenteRequest;
import pe.edu.upeu.lamb.planificacion.dto.DisponibilidadDocenteResponse;
import pe.edu.upeu.lamb.planificacion.entity.DisponibilidadDocente;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadDocenteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public DisponibilidadDocente toEntity(DisponibilidadDocenteRequest request) {
        return DisponibilidadDocente.builder()
                .idDocente(request.getIdDocente())
                .diaSemana(request.getDiaSemana())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .build();
    }

    public DisponibilidadDocenteResponse toResponse(DisponibilidadDocente disponibilidadDocente) {
        return DisponibilidadDocenteResponse.builder()
                .id(disponibilidadDocente.getId())
                .idDocente(disponibilidadDocente.getIdDocente())
                .idPeriodo(disponibilidadDocente.getPeriodo().getId())
                .codigoPeriodo(disponibilidadDocente.getPeriodo().getCodigo())
                .diaSemana(disponibilidadDocente.getDiaSemana())
                .horaInicio(disponibilidadDocente.getHoraInicio())
                .horaFin(disponibilidadDocente.getHoraFin())
                .build();
    }
}
