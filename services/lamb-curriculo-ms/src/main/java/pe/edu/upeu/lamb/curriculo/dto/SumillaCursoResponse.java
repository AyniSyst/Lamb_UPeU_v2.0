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
public class SumillaCursoResponse {
    private Long id;
    private Long idCurso;
    private String codigoCurso;
    private String naturaleza;
    private String proposito;
    private String contenido;
}
