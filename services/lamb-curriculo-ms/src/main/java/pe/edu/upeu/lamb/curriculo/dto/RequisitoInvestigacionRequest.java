package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoInvestigacionRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 100)
    private String tipoTrabajo;

    @NotNull
    @Min(0)
    @Max(20)
    private Integer creditos;

    @Size(max = 500)
    private String descripcion;
}
