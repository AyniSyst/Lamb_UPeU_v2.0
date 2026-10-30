package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idTipoContacto;

    @NotBlank
    @Size(max = 150)
    private String valor;
}
