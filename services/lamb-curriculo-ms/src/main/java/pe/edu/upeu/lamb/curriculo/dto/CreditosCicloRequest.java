package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditosCicloRequest {

    @NotNull
    private Long idCicloPlan;

    @NotNull
    @Min(0)
    @Max(40)
    private Integer creditosMinimos;

    @NotNull
    @Min(1)
    @Max(40)
    private Integer creditosMaximos;
}
