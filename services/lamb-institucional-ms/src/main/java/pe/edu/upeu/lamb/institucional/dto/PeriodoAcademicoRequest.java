package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PeriodoAcademicoRequest {

    @NotBlank
    private String nombre;

    @NotNull
    private Long idAnioAcademico;

    @NotNull
    private Long idTipoPeriodo;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
