package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetodoPagoRequest {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotNull
    private Boolean activo;
}
