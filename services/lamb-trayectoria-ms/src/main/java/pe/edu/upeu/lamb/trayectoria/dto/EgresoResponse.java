package pe.edu.upeu.lamb.trayectoria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EgresoResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String periodoEgreso,
        LocalDate fechaEgreso,
        Integer creditosTotales,
        BigDecimal promedioFinal,
        boolean todosRequisitosCumplidos,
        String numeroResolucion,
        String observacion,
        Long version
) {
}
