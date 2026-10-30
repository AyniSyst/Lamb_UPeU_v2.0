package pe.edu.upeu.lamb.institucional.dto;

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
public class PeriodoAcademicoResponse {
    private Long id;
    private String nombre;
    private Long idAnioAcademico;
    private Long idTipoPeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
