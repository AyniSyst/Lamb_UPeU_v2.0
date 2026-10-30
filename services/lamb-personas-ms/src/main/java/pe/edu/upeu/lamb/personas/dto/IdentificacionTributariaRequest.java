package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentificacionTributariaRequest {

    @NotNull
    private Long idPersona;

    @NotBlank
    @Size(max = 11)
    private String ruc;

    @Size(max = 150)
    private String razonSocial;
}
