package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoEquivalencia;
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
public class EquivalenciaPlanResponse {
    private Long id;
    private Long idPlanCursoOrigen;
    private Long idPlanCursoDestino;
    private TipoEquivalencia tipo;
    private String observacion;
}
