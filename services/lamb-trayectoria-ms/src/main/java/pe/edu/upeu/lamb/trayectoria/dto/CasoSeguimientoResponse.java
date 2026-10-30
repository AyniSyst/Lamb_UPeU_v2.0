package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDateTime;

public record CasoSeguimientoResponse(
        Long id,
        Long idAlerta,
        Long idPersona,
        Long idResponsable,
        String descripcionIntervencion,
        String estado,
        LocalDateTime fechaApertura,
        LocalDateTime fechaCierre,
        String resultado
) {
}
