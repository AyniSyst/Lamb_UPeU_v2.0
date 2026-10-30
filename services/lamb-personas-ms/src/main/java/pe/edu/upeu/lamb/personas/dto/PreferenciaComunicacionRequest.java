package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferenciaComunicacionRequest {

    @NotNull
    private Long idPersona;

    @NotBlank
    @Size(max = 60)
    private String canal;

    @Size(max = 60)
    private String horario;
}
