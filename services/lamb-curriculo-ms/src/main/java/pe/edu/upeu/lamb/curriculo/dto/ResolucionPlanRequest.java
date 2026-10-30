package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoResolucion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResolucionPlanRequest {

    @NotNull
    private Long idPlan;

    @NotBlank
    @Size(max = 50)
    private String numero;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private TipoResolucion tipo;

    @Size(max = 255)
    private String descripcion;
}
