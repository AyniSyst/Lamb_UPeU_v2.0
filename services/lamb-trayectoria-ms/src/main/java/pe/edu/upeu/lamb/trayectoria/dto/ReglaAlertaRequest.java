package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ReglaAlertaRequest(
        @NotBlank @Size(max = 100) String nombre,
        @NotBlank @Size(max = 30) String tipoAlerta,
        @NotNull BigDecimal umbral,
        @NotBlank @Size(max = 20) String nivelRiesgo,
        Boolean activo,
        @Size(max = 500) String descripcion
) {
}
