package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CambioUnidadAcademicaRequest {

    private Long idFacultad;

    private Long idEscuela;

    @NotBlank
    @Size(max = 100)
    private String tipoCambio;

    @NotBlank
    @Size(max = 100)
    private String numeroResolucion;

    @NotNull
    private LocalDate fechaCambio;

    @Size(max = 255)
    private String descripcion;
}
