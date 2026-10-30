package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionResponse {
    private Long id;
    private EvaluacionResponse evaluacion;
    private String idEstudiante;
    private BigDecimal nota;
    private LocalDateTime fechaRegistro;
}
