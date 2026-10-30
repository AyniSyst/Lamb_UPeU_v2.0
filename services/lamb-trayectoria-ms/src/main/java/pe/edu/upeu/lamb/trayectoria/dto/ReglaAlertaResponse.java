package pe.edu.upeu.lamb.trayectoria.dto;

import java.math.BigDecimal;

public record ReglaAlertaResponse(
        Long id,
        String nombre,
        String tipoAlerta,
        BigDecimal umbral,
        String nivelRiesgo,
        boolean activo,
        String descripcion
) {
}
