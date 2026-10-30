package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoNivelacion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CursoNivelacionRequest {

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idCurso;

    @NotNull
    private TipoNivelacion tipo;

    @NotNull
    @Min(1)
    private Integer cupoMaximo;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;
}
