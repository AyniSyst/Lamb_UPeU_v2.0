package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudCambioAulaRequest {

    @NotNull
    private Long idHorario;

    @NotNull
    private Long idAmbienteNuevo;

    @NotBlank
    @Size(max = 500)
    private String justificacion;
}
