package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoActividadFormacion;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoExtracurricularRequest {

    @NotNull
    private Long idPlan;

    @NotNull
    private TipoActividadFormacion tipo;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer creditosExigidos;
}
