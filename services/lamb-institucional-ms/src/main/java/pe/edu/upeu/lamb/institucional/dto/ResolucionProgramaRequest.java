package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResolucionProgramaRequest {

    @NotNull
    private Long idPrograma;

    @NotBlank
    @Size(max = 100)
    private String numeroResolucion;

    @Size(max = 100)
    private String tipoResolucion;

    private LocalDate fechaEmision;
}
