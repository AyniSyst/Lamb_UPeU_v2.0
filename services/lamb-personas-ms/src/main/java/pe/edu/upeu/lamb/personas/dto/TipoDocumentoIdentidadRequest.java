package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDocumentoIdentidadRequest {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @Size(max = 255)
    private String descripcion;
}
