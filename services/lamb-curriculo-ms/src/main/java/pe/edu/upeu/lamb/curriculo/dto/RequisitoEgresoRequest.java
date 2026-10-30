package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequisitoEgresoRequest {

    @NotNull
    private Long idPlan;

    @NotNull
    @Min(0)
    @Max(400)
    private Integer creditosObligatorios;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer creditosElectivos;

    @NotNull
    @Min(1)
    @Max(400)
    private Integer creditosTotales;

    @DecimalMin("0.0")
    private BigDecimal promedioMinimo;
}
