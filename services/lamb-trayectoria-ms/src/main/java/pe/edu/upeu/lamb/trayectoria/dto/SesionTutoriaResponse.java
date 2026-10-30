package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDateTime;

public record SesionTutoriaResponse(
        Long id,
        Long idTutoria,
        LocalDateTime fechaSesion,
        String tema,
        String observaciones,
        String recomendaciones,
        String compromisoEstudiante,
        String derivacionBienestar
) {
}
