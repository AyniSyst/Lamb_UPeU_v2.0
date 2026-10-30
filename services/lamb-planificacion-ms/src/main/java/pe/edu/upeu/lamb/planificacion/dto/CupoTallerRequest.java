package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupoTallerRequest {

    @NotNull
    private Long idGrupoTaller;

    @NotNull
    private Long idPrograma;

    @NotNull
    @Min(1)
    private Integer cantidad;
}
