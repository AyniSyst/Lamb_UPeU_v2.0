package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsablePagoRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idPersonaResponsable;
}
