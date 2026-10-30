package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutoridadFacultadRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idFacultad;

    @NotBlank
    @Size(max = 100)
    private String cargo;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
