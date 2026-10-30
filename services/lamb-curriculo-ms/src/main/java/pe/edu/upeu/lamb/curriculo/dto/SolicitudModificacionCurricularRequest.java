package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoModificacionCurricular;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudModificacionCurricularRequest {

    @NotNull
    private Long idPlan;

    @NotNull
    private TipoModificacionCurricular tipo;

    @NotBlank
    @Size(max = 1000)
    private String descripcion;

    @NotBlank
    @Size(max = 1000)
    private String justificacion;
}
