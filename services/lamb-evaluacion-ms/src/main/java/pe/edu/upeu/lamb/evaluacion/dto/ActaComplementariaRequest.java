package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ActaComplementariaRequest {

    @NotNull
    private Long idActa;

    @NotNull
    private Long idPersona;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal notaSustitutorio;

    @NotNull
    private LocalDate fecha;

    @Size(max = 255)
    private String observacion;
}
