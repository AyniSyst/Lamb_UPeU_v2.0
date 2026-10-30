package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ReconocimientoRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 30) String tipoReconocimiento,
        @NotBlank @Size(max = 500) String descripcion,
        @Size(max = 20) String periodo,
        Long idCompetencia,
        @Size(max = 1000) String autoevaluacion,
        @Size(max = 500) String evidenciaUrl,
        LocalDateTime fechaRegistro
) {
}
