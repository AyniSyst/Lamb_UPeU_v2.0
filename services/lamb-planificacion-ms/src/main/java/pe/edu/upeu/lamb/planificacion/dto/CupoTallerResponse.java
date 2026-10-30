package pe.edu.upeu.lamb.planificacion.dto;

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
public class CupoTallerResponse {
    private Long id;
    private Long idGrupoTaller;
    private String codigoGrupoTaller;
    private Long idPrograma;
    private Integer cantidad;
}
