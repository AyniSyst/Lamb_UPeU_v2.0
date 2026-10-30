package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idTipoDireccion;

    private Long idUbigeo;

    @Size(max = 255)
    private String direccion;
}
