package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificacionContactoRequest {

    @NotNull
    private Long idContacto;

    @NotNull
    private Boolean verificado;
}
