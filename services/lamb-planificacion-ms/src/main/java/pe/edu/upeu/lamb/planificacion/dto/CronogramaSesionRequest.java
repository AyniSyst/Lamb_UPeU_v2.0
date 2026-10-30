package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSesion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CronogramaSesionRequest {

    @NotNull
    private Long idSesion;

    @NotNull
    private LocalDate fechaProgramada;

    private LocalDate fechaEjecutada;

    @NotNull
    private EstadoSesion estado;

    @Size(max = 255)
    private String observacion;
}
