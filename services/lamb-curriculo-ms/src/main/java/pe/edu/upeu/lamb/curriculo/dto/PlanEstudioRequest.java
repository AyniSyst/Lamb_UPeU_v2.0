package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanEstudioRequest {

    @NotNull
    private Long idPrograma;

    @NotBlank
    @Size(max = 20)
    private String version;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    private Long idModalidad;
}
