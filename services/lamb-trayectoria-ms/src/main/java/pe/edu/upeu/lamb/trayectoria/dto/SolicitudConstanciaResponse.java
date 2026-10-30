package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDateTime;

public record SolicitudConstanciaResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String tipoDocumento,
        String motivo,
        String estado,
        String urlDocumentoGenerado,
        Long idResponsable,
        LocalDateTime fechaSolicitud,
        LocalDateTime fechaEntrega
) {
}
