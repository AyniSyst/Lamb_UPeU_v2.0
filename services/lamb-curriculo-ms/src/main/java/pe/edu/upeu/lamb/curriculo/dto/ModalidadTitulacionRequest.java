package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoTitulacion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModalidadTitulacionRequest {

    @NotNull
    private Long idPlan;

    @NotNull
    private TipoTitulacion tipo;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    private Boolean vigente;
}
