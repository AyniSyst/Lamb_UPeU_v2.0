package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SituacionCierreRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 20) String periodo,
        @NotBlank @Size(max = 20) String estadoAcademico,
        @Min(0) Integer creditosAprobados,
        BigDecimal promedioPeriodo,
        @Size(max = 500) String observacion,
        LocalDateTime fechaCierre
) {
}
