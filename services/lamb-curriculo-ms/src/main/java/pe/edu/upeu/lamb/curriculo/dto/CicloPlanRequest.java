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
public class CicloPlanRequest {

    @NotNull
    private Long idPlan;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer numero;

    @NotBlank
    @Size(max = 50)
    private String nombre;
}
