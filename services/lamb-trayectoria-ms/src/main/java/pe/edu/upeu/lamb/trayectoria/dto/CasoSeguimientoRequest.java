package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CasoSeguimientoRequest(
        @NotNull Long idAlerta,
        @NotNull Long idPersona,
        Long idResponsable,
        @NotBlank @Size(max = 1000) String descripcionIntervencion,
        @Size(max = 20) String estado,
        LocalDateTime fechaApertura,
        LocalDateTime fechaCierre,
        @Size(max = 500) String resultado
) {
}
