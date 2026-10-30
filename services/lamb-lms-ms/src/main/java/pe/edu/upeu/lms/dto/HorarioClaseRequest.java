package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lms.entity.DiaSemana;

import java.time.LocalTime;

@Getter
@Setter
public class HorarioClaseRequest {

    @NotNull
    private Long seccionCursoId;

    @NotNull
    private DiaSemana diaSemana;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFin;
}
