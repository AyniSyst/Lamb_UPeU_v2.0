package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ActaRectificacionRequest {

    @NotNull
    private Long idActa;

    @NotNull
    private Long idPersona;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal notaAnterior;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal notaNueva;

    @NotBlank
    @Size(max = 500)
    private String motivo;

    @Size(max = 50)
    private String resolucion;
}
