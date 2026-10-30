package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.TipoPregunta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Vista: resultado de la evaluacion docente por pregunta
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoPreguntaResponse {
    private Long idPregunta;
    private Integer orden;
    private String enunciado;
    private TipoPregunta tipo;
    private long respuestas;
    // ESCALA_1_5: promedio 1-5; SI_NO: porcentaje de "si"; ABIERTA: null
    private BigDecimal resultado;
}
