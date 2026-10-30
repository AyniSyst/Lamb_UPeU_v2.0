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
public class CursoExternoConvalidableResponse {
    private Long id;
    private Long idTabla;
    private String nombreTabla;
    private String codigoExterno;
    private String nombreExterno;
    private Integer creditosExterno;
    private Long idPlanCurso;
    private BigDecimal notaMinima;
}
