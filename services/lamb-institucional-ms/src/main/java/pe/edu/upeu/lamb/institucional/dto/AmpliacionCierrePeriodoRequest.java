package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AmpliacionCierrePeriodoRequest {

    @NotNull
    private Long idPeriodoAcademico;

    @NotBlank
    private String tipoAccion;

    private String numeroResolucion;

    private LocalDate fechaAccion;

    private String descripcion;
}
