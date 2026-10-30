package pe.edu.upeu.lamb.curriculo.dto;

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
public class RequisitoEgresoResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private Integer creditosObligatorios;
    private Integer creditosElectivos;
    private Integer creditosTotales;
    private BigDecimal promedioMinimo;
}
