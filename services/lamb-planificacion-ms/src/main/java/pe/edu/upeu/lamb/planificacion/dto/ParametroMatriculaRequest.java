package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ParametroMatriculaRequest {

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idPrograma;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    private LocalDate fechaLimiteRetiro;

    @NotNull
    private Boolean permiteExtemporanea;

    @DecimalMin("0.0")
    private BigDecimal recargoExtemporanea;
}
