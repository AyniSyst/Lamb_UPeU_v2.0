package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoTitulacion;
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
public class ModalidadTitulacionResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private TipoTitulacion tipo;
    private String descripcion;
    private Boolean vigente;
}
