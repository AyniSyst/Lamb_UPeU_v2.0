package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TablaConvalidacionRequest {

    @NotNull
    private Long idUniversidad;

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @Size(max = 50)
    private String resolucion;

    @NotNull
    private Boolean vigente;
}
