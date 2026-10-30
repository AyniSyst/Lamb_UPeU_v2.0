package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EgresoRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 20) String periodoEgreso,
        LocalDate fechaEgreso,
        @Min(0) Integer creditosTotales,
        BigDecimal promedioFinal,
        @Size(max = 50) String numeroResolucion,
        @Size(max = 500) String observacion
) {
}
