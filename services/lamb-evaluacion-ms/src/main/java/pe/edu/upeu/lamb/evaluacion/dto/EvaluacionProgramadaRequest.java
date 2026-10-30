package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EvaluacionProgramadaRequest {

    @NotNull
    private Long idGrupo;

    @NotNull
    private Long idComponente;

    @NotNull
    private LocalDate fecha;

    @Size(max = 255)
    private String descripcion;
}
