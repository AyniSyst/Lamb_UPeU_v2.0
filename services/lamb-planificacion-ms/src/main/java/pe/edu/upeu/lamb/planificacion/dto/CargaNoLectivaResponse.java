package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoCargaNoLectiva;
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
public class CargaNoLectivaResponse {
    private Long id;
    private Long idDocente;
    private Long idPeriodo;
    private String codigoPeriodo;
    private TipoCargaNoLectiva tipo;
    private String descripcion;
    private Integer horasSemanales;
}
