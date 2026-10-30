package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pe.edu.upeu.lamb.trayectoria.entity.EstadoTrayectoria;
import pe.edu.upeu.lamb.trayectoria.entity.TipoCambioAcademico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CambioAcademicoRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotNull TipoCambioAcademico tipoCambio,
        Long idPlanOrigen,
        Long idPlanDestino,
        Long idEscuelaOrigen,
        Long idEscuelaDestino,
        Long idSedeOrigen,
        Long idSedeDestino,
        @NotNull EstadoTrayectoria estadoAnterior,
        @NotNull EstadoTrayectoria estadoNuevo,
        @Size(max = 500) String motivo,
        @Size(max = 50) String numeroResolucion,
        @NotNull LocalDateTime fechaCambio,
        LocalDate fechaVigencia
) {
}
