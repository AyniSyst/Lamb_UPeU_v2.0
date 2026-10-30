package pe.edu.upeu.lamb.trayectoria.dto;

import pe.edu.upeu.lamb.trayectoria.entity.EstadoTrayectoria;

import java.math.BigDecimal;

public record AvanceCurricularResponse(
        Long idTrayectoria,
        Long idPersona,
        Integer creditosTotalesPlan,
        Integer creditosAprobados,
        Integer cursosRegistrados,
        Integer cursosAprobados,
        Integer cursosDesaprobados,
        BigDecimal avancePorcentaje,
        EstadoTrayectoria estado
) {
}
