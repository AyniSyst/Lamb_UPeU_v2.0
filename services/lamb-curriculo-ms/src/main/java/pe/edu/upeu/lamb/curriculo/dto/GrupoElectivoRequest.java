package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GrupoElectivoRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer cantidadElegir;

    @NotEmpty
    private List<@NotNull Long> idPlanCursos;
}
