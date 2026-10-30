package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoEmergenciaRequest {

    @NotNull
    private Long idPersona;

    @NotBlank
    @Size(max = 150)
    private String nombreContacto;

    @Size(max = 30)
    private String telefono;

    @Size(max = 60)
    private String parentesco;
}
