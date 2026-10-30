package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VigenciaCohorteRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 20)
    private String cohorteInicio;

    @Size(max = 20)
    private String cohorteFin;

    @Size(max = 255)
    private String observacion;
}
