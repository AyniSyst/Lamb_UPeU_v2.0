package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDateTime;

public record InformeTrayectoriaResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String periodo,
        Long idApoderado,
        String resumen,
        String urlDocumento,
        LocalDateTime fechaGeneracion
) {
}
