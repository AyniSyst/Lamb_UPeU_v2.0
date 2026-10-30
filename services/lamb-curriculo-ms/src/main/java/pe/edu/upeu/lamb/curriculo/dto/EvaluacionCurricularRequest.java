package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.ResultadoEvaluacionCurricular;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EvaluacionCurricularRequest {

    @NotNull
    private Long idPlan;

    private Long idComision;

    @NotNull
    private Long idPeriodo;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private ResultadoEvaluacionCurricular resultado;

    @Size(max = 2000)
    private String conclusiones;
}
