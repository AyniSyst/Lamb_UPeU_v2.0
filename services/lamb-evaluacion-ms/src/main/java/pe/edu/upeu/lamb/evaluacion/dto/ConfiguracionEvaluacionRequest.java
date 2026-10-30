package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConfiguracionEvaluacionRequest {

    @NotNull
    private Long idGrupo;

    @NotNull
    private Long idComponente;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal peso;
}
