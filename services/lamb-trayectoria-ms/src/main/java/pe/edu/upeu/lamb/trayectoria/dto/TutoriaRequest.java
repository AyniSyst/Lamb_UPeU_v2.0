package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record TutoriaRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotNull Long idTutor,
        Long idProgramaTutoria,
        @Size(max = 100) String programaTutoria,
        @NotBlank @Size(max = 20) String periodo,
        @Size(max = 20) String estado,
        @NotNull LocalDate fechaInicio,
        LocalDate fechaFin,
        @Valid List<SesionTutoriaRequest> sesiones
) {
}
