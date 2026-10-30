package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoCierreFusion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudCierreFusionGrupoRequest {

    @NotNull
    private TipoCierreFusion tipo;

    @NotNull
    private Long idGrupo;

    private Long idGrupoDestino;

    @NotBlank
    @Size(max = 500)
    private String justificacion;
}
