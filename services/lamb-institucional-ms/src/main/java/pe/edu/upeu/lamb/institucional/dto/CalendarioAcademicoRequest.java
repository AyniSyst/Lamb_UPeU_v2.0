package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarioAcademicoRequest {

    @NotBlank
    private String nombre;

    @NotNull
    private Long idPeriodoAcademico;

    private String descripcion;
}
