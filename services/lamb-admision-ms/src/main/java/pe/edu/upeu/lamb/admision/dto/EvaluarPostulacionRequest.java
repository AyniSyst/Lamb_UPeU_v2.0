package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EvaluarPostulacionRequest {

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "20.0")
    private BigDecimal puntajeExamen;
}
