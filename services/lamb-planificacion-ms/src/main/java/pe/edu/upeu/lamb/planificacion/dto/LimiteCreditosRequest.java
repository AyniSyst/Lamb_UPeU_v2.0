package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LimiteCreditosRequest {

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idPrograma;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer ciclo;

    @NotNull
    @Min(0)
    @Max(40)
    private Integer creditosMinimos;

    @NotNull
    @Min(1)
    @Max(40)
    private Integer creditosMaximos;
}
