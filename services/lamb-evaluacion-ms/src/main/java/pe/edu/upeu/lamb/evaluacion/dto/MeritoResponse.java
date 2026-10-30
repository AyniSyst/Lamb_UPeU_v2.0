package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Vista: una fila del cuadro de meritos del periodo
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeritoResponse {
    private int posicion;
    private Long idPersona;
    private BigDecimal promedio;
    private int cursos;
    private boolean tercioSuperior;
}
