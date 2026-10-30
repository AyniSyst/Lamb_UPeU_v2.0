package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorreccionIdentificacionRequest {

    @NotNull
    private Long idPersona;

    @NotBlank
    @Size(max = 50)
    private String campoCorregido;

    @Size(max = 100)
    private String valorAnterior;

    @NotBlank
    @Size(max = 100)
    private String valorNuevo;

    @NotBlank
    @Size(max = 255)
    private String sustento;
}
