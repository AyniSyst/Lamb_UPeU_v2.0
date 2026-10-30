package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CriterioRubricaRequest {

    @NotNull
    private Long idRubrica;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer orden;

    @NotBlank
    @Size(max = 500)
    private String descripcion;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal puntajeMaximo;
}
