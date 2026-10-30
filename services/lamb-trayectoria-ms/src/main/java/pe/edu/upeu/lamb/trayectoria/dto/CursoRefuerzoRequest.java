package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoRefuerzoRequest(
        @NotBlank @Size(max = 20) String codigoCurso,
        @Size(max = 150) String nombreCurso,
        @NotNull @Min(1) @Max(4) Integer numeroRepitencia,
        @Size(max = 500) String actividadAsignada,
        Boolean completado,
        @Size(max = 300) String observacion
) {
}
