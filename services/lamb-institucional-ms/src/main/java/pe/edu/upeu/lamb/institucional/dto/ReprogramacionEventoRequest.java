package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReprogramacionEventoRequest {

    @NotNull
    private Long idEventoCalendario;

    private String numeroResolucion;

    private LocalDate fechaInicioNueva;

    private LocalDate fechaFinNueva;

    private String motivo;
}
