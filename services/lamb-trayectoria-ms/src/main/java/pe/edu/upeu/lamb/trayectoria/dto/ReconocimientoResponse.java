package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDateTime;

public record ReconocimientoResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String tipoReconocimiento,
        String descripcion,
        String periodo,
        Long idCompetencia,
        String autoevaluacion,
        String evidenciaUrl,
        LocalDateTime fechaRegistro
) {
}
