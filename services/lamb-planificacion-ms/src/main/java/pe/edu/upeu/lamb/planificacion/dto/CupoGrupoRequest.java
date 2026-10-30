package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoCupo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupoGrupoRequest {

    @NotNull
    private Long idGrupo;

    @NotNull
    private TipoCupo tipo;

    @NotNull
    @Min(1)
    private Integer cantidad;
}
