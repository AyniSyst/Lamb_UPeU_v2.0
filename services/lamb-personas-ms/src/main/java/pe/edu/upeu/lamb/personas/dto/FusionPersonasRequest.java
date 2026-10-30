package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FusionPersonasRequest {

    @NotNull
    private Long idPersonaOrigen;

    @NotNull
    private Long idPersonaDestino;

    @Size(max = 255)
    private String motivo;
}
