package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoCargaNoLectiva;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargaNoLectivaRequest {

    @NotNull
    private Long idDocente;

    @NotNull
    private Long idPeriodo;

    @NotNull
    private TipoCargaNoLectiva tipo;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    @Min(1)
    @Max(40)
    private Integer horasSemanales;
}
