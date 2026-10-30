package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record SesionTutoriaRequest(
        Long idTutoria,
        @NotNull LocalDateTime fechaSesion,
        @NotBlank @Size(max = 200) String tema,
        @Size(max = 1000) String observaciones,
        @Size(max = 1000) String recomendaciones,
        @Size(max = 500) String compromisoEstudiante,
        @Size(max = 100) String derivacionBienestar
) {
}
