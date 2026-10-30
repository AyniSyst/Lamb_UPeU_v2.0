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
public class PerfilIngresoResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private String descripcion;
    private String conocimientos;
    private String habilidades;
    private String actitudes;
}
