package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RubricaRequest {

    @NotNull
    private Long idComponente;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @Size(max = 500)
    private String descripcion;
}
