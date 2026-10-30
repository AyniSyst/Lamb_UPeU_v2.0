package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lms.entity.TipoPregunta;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaResponse {
    private Long id;
    private EvaluacionResponse evaluacion;
    private String enunciado;
    private TipoPregunta tipo;
    private BigDecimal puntaje;
}
