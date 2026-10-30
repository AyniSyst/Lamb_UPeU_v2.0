package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NivelRiesgoRequest(
        @NotBlank @Size(max = 30) String codigo,
        @NotBlank @Size(max = 100) String nombre,
        @Size(max = 300) String descripcion,
        Boolean activo
) {
}
