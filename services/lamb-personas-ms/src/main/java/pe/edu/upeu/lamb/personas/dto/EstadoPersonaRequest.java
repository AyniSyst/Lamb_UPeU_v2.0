package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.PersonaEstado;

@Getter
@Setter
public class EstadoPersonaRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private PersonaEstado estado;

    @Size(max = 255)
    private String motivo;
}
