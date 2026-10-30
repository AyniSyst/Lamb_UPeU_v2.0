package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record InformeTrayectoriaRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 20) String periodo,
        Long idApoderado,
        @NotBlank @Size(max = 1000) String resumen,
        @Size(max = 500) String urlDocumento,
        LocalDateTime fechaGeneracion
) {
}
