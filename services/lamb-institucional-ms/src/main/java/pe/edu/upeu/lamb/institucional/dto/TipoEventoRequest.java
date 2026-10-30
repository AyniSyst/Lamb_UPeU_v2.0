package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoEventoRequest {

    @NotBlank
    private String nombre;

    private String descripcion;
}
