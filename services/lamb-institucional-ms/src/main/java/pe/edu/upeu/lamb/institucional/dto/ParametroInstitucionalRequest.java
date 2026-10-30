package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametroInstitucionalRequest {

    @NotBlank
    private String clave;

    private String valor;

    private String descripcion;
}
