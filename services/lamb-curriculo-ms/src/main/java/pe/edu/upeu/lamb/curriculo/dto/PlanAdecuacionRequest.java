package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlanAdecuacionRequest {

    @NotNull
    private Long idPlanOrigen;

    @NotNull
    private Long idPlanDestino;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @Size(max = 50)
    private String resolucion;

    @NotNull
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Size(max = 1000)
    private String descripcion;
}
