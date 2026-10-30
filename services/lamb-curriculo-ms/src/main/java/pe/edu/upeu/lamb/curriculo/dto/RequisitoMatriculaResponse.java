package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoRequisitoMatricula;
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
public class RequisitoMatriculaResponse {
    private Long id;
    private Long idPlanCurso;
    private TipoRequisitoMatricula tipo;
    private Long idCursoRequisito;
    private Integer creditosMinimos;
    private Integer cicloMinimo;
    private String descripcion;
}
