package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialEstadoPersonaRequest {

    @NotNull
    private Long idPersona;

    @Size(max = 30)
    private String estadoAnterior;

    @NotBlank
    @Size(max = 30)
    private String estadoNuevo;

    @Size(max = 255)
    private String observacion;
}
