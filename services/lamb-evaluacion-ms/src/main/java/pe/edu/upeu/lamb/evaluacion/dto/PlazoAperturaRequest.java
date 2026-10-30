package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlazoAperturaRequest {

    @NotNull
    private Long idGrupo;

    @NotNull
    private Long idDocente;

    private Long idSolicitud;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @Size(max = 255)
    private String motivo;
}
