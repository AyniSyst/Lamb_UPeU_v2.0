package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionSedeRequest {

    @NotNull
    private Long idSede;

    @NotNull
    private Long idUbigeo;

    @NotBlank
    @Size(max = 255)
    private String direccionDetalle;

    @Size(max = 255)
    private String referencia;
}
