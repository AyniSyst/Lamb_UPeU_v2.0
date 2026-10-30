package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfesionReligiosaRequest {

    @NotNull
    private Long idPersona;

    @Size(max = 100)
    private String religion;
}
