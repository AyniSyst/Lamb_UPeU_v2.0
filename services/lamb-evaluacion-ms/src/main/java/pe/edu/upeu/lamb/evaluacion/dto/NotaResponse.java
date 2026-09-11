package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.TipoEvaluacion;
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
public class NotaResponse {
    private Long id;
    private Long idPersona;
    private Long idSeccion;
    private TipoEvaluacion tipoEvaluacion;
    private BigDecimal calificacion;
    private String periodo;
}
