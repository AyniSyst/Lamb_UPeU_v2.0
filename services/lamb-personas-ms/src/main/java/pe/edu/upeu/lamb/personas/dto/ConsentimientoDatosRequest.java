package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentimientoDatosRequest {

    @NotNull
    private Long idPersona;

    @NotBlank
    @Size(max = 100)
    private String tipoConsentimiento;

    @NotNull
    private Boolean aceptado;
}
