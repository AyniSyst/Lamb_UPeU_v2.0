package pe.edu.upeu.lamb.curriculo.dto;

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
    private Long idCursoCatalogo;
    private String codigoCurso;
    private String nombreCurso;
    private Integer ciclo;
    private Integer creditos;
    private String tipo;
    private String areaCurricular;
    private List<String> prerrequisitos;
}
