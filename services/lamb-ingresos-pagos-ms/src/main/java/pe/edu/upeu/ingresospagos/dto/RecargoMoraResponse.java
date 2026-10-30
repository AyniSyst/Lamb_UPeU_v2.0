package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.TipoCalculoRecargo;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecargoMoraResponse {
    private Long id;
    private String nombre;
    private TipoCalculoRecargo tipoCalculo;
    private BigDecimal valor;
    private Integer diasGracia;
    private Boolean activo;
}
