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
public class CriterioRubricaResponse {
    private Long id;
    private Long idRubrica;
    private String nombreRubrica;
    private Integer orden;
    private String descripcion;
    private BigDecimal puntajeMaximo;
}
