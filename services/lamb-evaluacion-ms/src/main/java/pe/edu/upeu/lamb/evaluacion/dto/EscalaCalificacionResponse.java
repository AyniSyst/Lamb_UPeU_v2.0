package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.TipoRedondeo;
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
public class EscalaCalificacionResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private BigDecimal notaMinima;
    private BigDecimal notaMaxima;
    private BigDecimal notaAprobatoria;
    private Integer decimales;
    private TipoRedondeo redondeo;
    private Boolean vigente;
}
