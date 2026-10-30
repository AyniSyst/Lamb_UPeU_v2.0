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
public class CursoInvestigacionResponse {
    private Long id;
    private Long idLinea;
    private String codigoLinea;
    private Long idPlanCurso;
}
