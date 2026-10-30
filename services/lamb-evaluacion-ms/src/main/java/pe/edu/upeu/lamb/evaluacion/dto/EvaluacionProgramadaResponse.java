package pe.edu.upeu.lamb.evaluacion.dto;

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
public class EvaluacionProgramadaResponse {
    private Long id;
    private Long idGrupo;
    private Long idComponente;
    private String codigoComponente;
    private LocalDate fecha;
    private String descripcion;
}
