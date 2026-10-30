package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilEgresoRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 2000)
    private String descripcion;
}
