package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodoProgramaSedeRequest {

    @NotNull
    private Long idPeriodoAcademico;

    @NotNull
    private Long idPrograma;

    @NotNull
    private Long idSede;

    private Boolean vigente;
}
