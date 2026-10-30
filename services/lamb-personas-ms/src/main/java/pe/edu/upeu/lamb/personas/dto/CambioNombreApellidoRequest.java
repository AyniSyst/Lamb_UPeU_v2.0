package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambioNombreApellidoRequest {

    @NotNull
    private Long idPersona;

    @Size(max = 150)
    private String nombreAnterior;

    @NotBlank
    @Size(max = 150)
    private String nombreNuevo;

    @NotBlank
    @Size(max = 255)
    private String sustento;
}
