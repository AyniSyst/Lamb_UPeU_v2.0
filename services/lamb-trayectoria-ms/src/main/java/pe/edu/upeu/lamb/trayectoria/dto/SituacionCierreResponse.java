package pe.edu.upeu.lamb.trayectoria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SituacionCierreResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String periodo,
        String estadoAcademico,
        Integer creditosAprobados,
        BigDecimal promedioPeriodo,
        String observacion,
        LocalDateTime fechaCierre
) {
}
