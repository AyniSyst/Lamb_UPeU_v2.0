package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametroSedeEscuelaRequest {

    @NotBlank
    private String clave;

    private String valor;

    private Long idSede;

    private Long idEscuela;
}
