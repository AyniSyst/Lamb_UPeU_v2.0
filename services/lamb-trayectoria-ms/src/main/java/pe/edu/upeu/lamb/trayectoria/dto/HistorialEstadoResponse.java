package pe.edu.upeu.lamb.trayectoria.dto;

import pe.edu.upeu.lamb.trayectoria.entity.EstadoTrayectoria;
import pe.edu.upeu.lamb.trayectoria.entity.TipoCambioAcademico;

import java.time.LocalDateTime;

public record HistorialEstadoResponse(
        Long id,
        TipoCambioAcademico tipoCambio,
        EstadoTrayectoria estadoAnterior,
        EstadoTrayectoria estadoNuevo,
        String motivo,
        String numeroResolucion,
        LocalDateTime fechaCambio
) {
}
