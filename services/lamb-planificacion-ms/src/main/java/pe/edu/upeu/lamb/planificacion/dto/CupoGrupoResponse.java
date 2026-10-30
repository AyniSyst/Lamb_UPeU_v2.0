package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoCupo;
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
public class CupoGrupoResponse {
    private Long id;
    private Long idGrupo;
    private String codigoGrupo;
    private TipoCupo tipo;
    private Integer cantidad;
}
