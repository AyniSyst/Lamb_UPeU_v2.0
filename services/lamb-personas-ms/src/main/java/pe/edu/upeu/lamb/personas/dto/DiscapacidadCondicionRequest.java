package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscapacidadCondicionRequest {

    @NotNull
    private Long idPersona;

    @Size(max = 100)
    private String tipo;

    @Size(max = 255)
    private String observacion;
}
