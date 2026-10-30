package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.ResultadoEvaluacionCurricular;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionCurricularResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private Long idComision;
    private String nombreComision;
    private Long idPeriodo;
    private LocalDate fecha;
    private ResultadoEvaluacionCurricular resultado;
    private String conclusiones;
}
