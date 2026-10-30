package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoOferta;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaAcademicaRequest {

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idPrograma;

    @NotNull
    private Long idCurso;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer ciclo;

    @NotNull
    private EstadoOferta estado;
}
