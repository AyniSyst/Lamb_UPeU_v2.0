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
public class VigenciaCohorteResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private String cohorteInicio;
    private String cohorteFin;
    private String observacion;
}
