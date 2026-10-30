package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Vista: nota final de un estudiante en un grupo (no se guarda, se calcula)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaFinalResponse {
    private Long idGrupo;
    private Long idPersona;
    private BigDecimal notaFinal;
    private boolean aprobado;
}
