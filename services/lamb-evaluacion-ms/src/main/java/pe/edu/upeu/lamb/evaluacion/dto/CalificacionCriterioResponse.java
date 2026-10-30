package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionCriterioResponse {
    private Long id;
    private Long idCriterio;
    private Long idGrupo;
    private Long idPersona;
    private BigDecimal puntaje;
}
