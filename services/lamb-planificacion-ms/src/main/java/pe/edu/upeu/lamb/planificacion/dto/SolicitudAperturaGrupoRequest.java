package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudAperturaGrupoRequest {

    @NotNull
    private Long idOferta;

    @NotNull
    @Min(1)
    private Integer cupoPropuesto;

    @NotBlank
    @Size(max = 500)
    private String justificacion;
}
