package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistroAsistenciaRequest {

    @NotNull
    private Long idGrupo;

    @NotNull
    private Long idPersona;

    private Long idSesionClase;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private EstadoAsistencia estado;
}
