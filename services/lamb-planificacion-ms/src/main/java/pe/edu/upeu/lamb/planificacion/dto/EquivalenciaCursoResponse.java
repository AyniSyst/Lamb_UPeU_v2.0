package pe.edu.upeu.lamb.planificacion.dto;

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
public class EquivalenciaCursoResponse {
    private Long id;
    private Long idCurso;
    private String codigoCurso;
    private Long idProgramaOrigen;
    private Long idCursoEquivalente;
    private String codigoCursoEquivalente;
    private Long idProgramaEquivalente;
    private String observacion;
}
