package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ControlRepitenciaRequest(
        @NotNull Long idPersona,
        @NotBlank @Size(max = 20) String codigoCurso,
        @NotNull @Min(1) @Max(4) Integer numeroRepitencia,
        @NotBlank @Size(max = 20) String periodo,
        @Size(max = 20) String estado,
        @Size(max = 500) String observacion,
        LocalDateTime fechaRegistro
) {
}
