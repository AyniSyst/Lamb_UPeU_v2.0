package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoEquivalencia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquivalenciaPlanRequest {

    @NotNull
    private Long idPlanCursoOrigen;

    @NotNull
    private Long idPlanCursoDestino;

    @NotNull
    private TipoEquivalencia tipo;

    @Size(max = 255)
    private String observacion;
}
