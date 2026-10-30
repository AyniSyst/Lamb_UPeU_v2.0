package pe.edu.upeu.lamb.trayectoria.dto;

import pe.edu.upeu.lamb.trayectoria.entity.EstadoTrayectoria;
import pe.edu.upeu.lamb.trayectoria.entity.TipoCambioAcademico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CambioAcademicoResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        TipoCambioAcademico tipoCambio,
        Long idPlanOrigen,
        Long idPlanDestino,
        Long idEscuelaOrigen,
        Long idEscuelaDestino,
        Long idSedeOrigen,
        Long idSedeDestino,
        EstadoTrayectoria estadoAnterior,
        EstadoTrayectoria estadoNuevo,
        String motivo,
        String numeroResolucion,
        LocalDateTime fechaCambio,
        LocalDate fechaVigencia
) {
}
