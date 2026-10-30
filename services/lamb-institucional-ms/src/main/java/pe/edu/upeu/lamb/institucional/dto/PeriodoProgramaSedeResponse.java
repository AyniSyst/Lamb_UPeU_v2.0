package pe.edu.upeu.lamb.institucional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoProgramaSedeResponse {
    private Long id;
    private Long idPeriodoAcademico;
    private Long idPrograma;
    private Long idSede;
    private Boolean vigente;
}
