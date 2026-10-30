package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargaLectivaRequest {

    @NotNull
    private Long idDocente;

    @NotNull
    private Long idPeriodo;

    @NotNull
    @Min(1)
    @Max(40)
    private Integer horasMaximas;
}
