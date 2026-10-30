package pe.edu.upeu.lms.dto;

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
public class RubricaEvaluacionResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal puntajeMaximo;
    private Boolean activo;
}
