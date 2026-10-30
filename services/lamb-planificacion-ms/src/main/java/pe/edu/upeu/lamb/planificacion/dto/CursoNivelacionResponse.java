package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.TipoNivelacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoNivelacionResponse {
    private Long id;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Long idCurso;
    private String codigoCurso;
    private TipoNivelacion tipo;
    private Integer cupoMaximo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
