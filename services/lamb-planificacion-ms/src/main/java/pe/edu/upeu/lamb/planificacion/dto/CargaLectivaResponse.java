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
public class CargaLectivaResponse {
    private Long id;
    private Long idDocente;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Integer horasMaximas;
}
