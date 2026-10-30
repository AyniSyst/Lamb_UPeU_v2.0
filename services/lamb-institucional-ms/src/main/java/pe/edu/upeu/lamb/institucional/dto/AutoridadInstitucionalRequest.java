package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutoridadInstitucionalRequest {

    @NotNull
    private Long idPersona;

    @NotBlank
    @Size(max = 100)
    private String cargo;

    @NotNull
    private Long idInstitucion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
