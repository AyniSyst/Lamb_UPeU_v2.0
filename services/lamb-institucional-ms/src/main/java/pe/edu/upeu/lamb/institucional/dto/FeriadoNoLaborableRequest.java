package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FeriadoNoLaborableRequest {

    @NotBlank
    private String nombre;

    @NotNull
    private LocalDate fecha;

    private String descripcion;
}
