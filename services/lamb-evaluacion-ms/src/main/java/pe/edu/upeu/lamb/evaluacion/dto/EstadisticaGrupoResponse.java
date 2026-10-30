package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Vista: promedio del grupo y estadistica de aprobados / desaprobados
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticaGrupoResponse {
    private Long idGrupo;
    private int estudiantes;
    private BigDecimal promedio;
    private int aprobados;
    private int desaprobados;
    private BigDecimal porcentajeAprobados;
}
