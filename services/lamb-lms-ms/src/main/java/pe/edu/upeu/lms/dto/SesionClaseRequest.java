package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SesionClaseRequest {

    @NotNull
    private Long seccionCursoId;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFin;

    @Size(max = 255)
    private String enlaceVirtual;

    @NotNull
    private Boolean activo;
}
