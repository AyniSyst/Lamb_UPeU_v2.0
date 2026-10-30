package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SesionClaseRequest {

    @NotNull
    private Long idSilabo;

    @NotNull
    @Min(1)
    @Max(64)
    private Integer numero;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer semana;

    @NotBlank
    @Size(max = 255)
    private String tema;

    private Long idUnidadAprendizaje;

    @Size(max = 1000)
    private String actividades;
}
