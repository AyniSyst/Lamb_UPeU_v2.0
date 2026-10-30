package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoActividadFormacion;
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
public class RequisitoExtracurricularResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private TipoActividadFormacion tipo;
    private Integer creditosExigidos;
}
