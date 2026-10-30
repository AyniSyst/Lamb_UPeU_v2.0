package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferenciaCursoDocenteRequest {

    @NotNull
    private Long idDocente;

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idCurso;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer prioridad;
}
