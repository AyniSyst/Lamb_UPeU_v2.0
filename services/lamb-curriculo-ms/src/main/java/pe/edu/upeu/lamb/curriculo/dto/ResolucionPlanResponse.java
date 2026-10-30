package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoResolucion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionPlanResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private String numero;
    private LocalDate fecha;
    private TipoResolucion tipo;
    private String descripcion;
}
