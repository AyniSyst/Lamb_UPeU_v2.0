package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SolicitudAperturaNotasRequest {

    @NotNull
    private Long idGrupo;

    @NotBlank
    @Size(max = 500)
    private String motivo;

    @NotNull
    private LocalDate fechaInicioPropuesta;

    @NotNull
    private LocalDate fechaFinPropuesta;
}
