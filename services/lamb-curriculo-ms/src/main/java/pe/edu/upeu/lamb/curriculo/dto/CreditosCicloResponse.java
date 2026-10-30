package pe.edu.upeu.lamb.curriculo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditosCicloResponse {
    private Long id;
    private Long idCicloPlan;
    private String nombreCicloPlan;
    private Integer creditosMinimos;
    private Integer creditosMaximos;
}
