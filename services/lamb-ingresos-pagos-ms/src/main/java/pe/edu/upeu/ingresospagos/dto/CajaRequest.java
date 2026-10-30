package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CajaRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 150)
    private String ubicacion;

    @NotNull
    private Boolean activo;
}
