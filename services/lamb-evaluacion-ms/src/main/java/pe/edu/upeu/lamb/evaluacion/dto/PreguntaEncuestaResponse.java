package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.TipoPregunta;
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
public class PreguntaEncuestaResponse {
    private Long id;
    private Long idEncuesta;
    private String nombreEncuesta;
    private Integer orden;
    private String enunciado;
    private TipoPregunta tipo;
}
