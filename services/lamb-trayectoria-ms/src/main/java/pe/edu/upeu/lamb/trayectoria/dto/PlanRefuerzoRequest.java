package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record PlanRefuerzoRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 20) String periodo,
        @NotBlank @Size(max = 500) String objetivo,
        @Size(max = 20) String estado,
        Long idResponsable,
        @NotNull LocalDate fechaInicio,
        LocalDate fechaFin,
        @Size(max = 500) String observacion,
        @Valid List<CursoRefuerzoRequest> cursos
) {
}
