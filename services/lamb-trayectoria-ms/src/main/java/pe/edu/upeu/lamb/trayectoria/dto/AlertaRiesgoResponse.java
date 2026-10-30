package pe.edu.upeu.lamb.trayectoria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AlertaRiesgoResponse(
        Long id,
        Long idTrayectoria,
        Long idPersona,
        String nivelRiesgo,
        String tipoAlerta,
        String descripcion,
        BigDecimal valorDetectado,
        BigDecimal umbralRegla,
        Integer numeroRepitencia,
        String codigoCurso,
        String estado,
        LocalDateTime fechaDeteccion,
        LocalDateTime fechaCierre
) {
}
