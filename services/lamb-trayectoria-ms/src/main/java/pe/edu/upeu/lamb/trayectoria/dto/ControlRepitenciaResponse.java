package pe.edu.upeu.lamb.trayectoria.dto;

import java.time.LocalDateTime;

public record ControlRepitenciaResponse(
        Long id,
        Long idPersona,
        String codigoCurso,
        Integer numeroRepitencia,
        String periodo,
        String estado,
        String observacion,
        LocalDateTime fechaRegistro
) {
}
