package pe.edu.upeu.lamb.trayectoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AlertaRiesgoRequest(
        @NotNull Long idTrayectoria,
        @NotNull Long idPersona,
        @NotBlank @Size(max = 20) String nivelRiesgo,
        @NotBlank @Size(max = 30) String tipoAlerta,
        @NotBlank @Size(max = 500) String descripcion,
        BigDecimal valorDetectado,
        BigDecimal umbralRegla,
        Integer numeroRepitencia,
        @Size(max = 20) String codigoCurso,
        @Size(max = 20) String estado,
        LocalDateTime fechaDeteccion,
        LocalDateTime fechaCierre
) {
}
