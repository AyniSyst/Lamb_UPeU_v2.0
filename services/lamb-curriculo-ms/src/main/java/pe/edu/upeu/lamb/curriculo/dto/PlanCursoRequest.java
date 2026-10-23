package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoCurso;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanCursoRequest {

    @NotBlank
    @Size(max = 20)
    private String codigoCurso;

    @NotBlank
    @Size(max = 150)
    private String nombreCurso;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer ciclo;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer creditos;

    @NotNull
    private TipoCurso tipo;
}
