package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionEvaluacionResponse {
    private Long id;
    private Long idGrupo;
    private Long idComponente;
    private String codigoComponente;
    private BigDecimal peso;
}
