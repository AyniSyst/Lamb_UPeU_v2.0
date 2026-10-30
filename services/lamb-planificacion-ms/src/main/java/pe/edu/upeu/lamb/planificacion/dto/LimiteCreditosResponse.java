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
public class LimiteCreditosResponse {
    private Long id;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Long idPrograma;
    private Integer ciclo;
    private Integer creditosMinimos;
    private Integer creditosMaximos;
}
