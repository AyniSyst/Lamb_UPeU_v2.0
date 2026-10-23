package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoCurso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanCursoResponse {
    private Long id;
    private String codigoCurso;
    private String nombreCurso;
    private Integer ciclo;
    private Integer creditos;
    private TipoCurso tipo;
    private List<String> prerrequisitos;
}
