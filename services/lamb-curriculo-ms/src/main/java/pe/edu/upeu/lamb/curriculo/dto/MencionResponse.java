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
public class MencionResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private String nombre;
    private Integer creditosRequeridos;
    private String descripcion;
}
