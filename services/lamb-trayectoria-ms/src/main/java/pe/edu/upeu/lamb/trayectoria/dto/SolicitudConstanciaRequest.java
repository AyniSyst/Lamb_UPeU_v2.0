package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record SolicitudConstanciaRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 30) String tipoDocumento,
        @Size(max = 500) String motivo,
        @Size(max = 20) String estado,
        @Size(max = 500) String urlDocumentoGenerado,
        Long idResponsable,
        LocalDateTime fechaSolicitud,
        LocalDateTime fechaEntrega
) {
}
