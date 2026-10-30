package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoPracticasRequest {

    @NotNull
    private Long idPlan;

    @NotNull
    @Min(1)
    @Max(2000)
    private Integer horasMinimas;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer cicloDesde;

    @Size(max = 500)
    private String descripcion;
}
