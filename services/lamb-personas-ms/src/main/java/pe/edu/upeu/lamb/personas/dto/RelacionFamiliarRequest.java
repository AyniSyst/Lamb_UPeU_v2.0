package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelacionFamiliarRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idPersonaRelacionada;

    @NotNull
    private Long idTipoParentesco;
}
