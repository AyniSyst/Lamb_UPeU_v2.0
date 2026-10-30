package pe.edu.upeu.lamb.evaluacion.dto;

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
public class RespuestaEncuestaResponse {
    private Long id;
    private Long idPregunta;
    private Long idGrupo;
    private Long idDocente;
    private Integer valorEscala;
    private String valorTexto;
}
