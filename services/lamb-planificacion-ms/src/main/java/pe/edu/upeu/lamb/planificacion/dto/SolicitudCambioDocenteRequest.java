package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudCambioDocenteRequest {

    @NotNull
    private Long idAsignacion;

    @NotNull
    private Long idDocenteNuevo;

    @NotBlank
    @Size(max = 500)
    private String justificacion;
}
