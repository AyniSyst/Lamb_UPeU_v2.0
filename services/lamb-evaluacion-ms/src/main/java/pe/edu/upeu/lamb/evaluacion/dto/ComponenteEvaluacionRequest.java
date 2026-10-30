package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.TipoComponente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponenteEvaluacionRequest {

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    private TipoComponente tipo;

    @Size(max = 255)
    private String descripcion;
}
