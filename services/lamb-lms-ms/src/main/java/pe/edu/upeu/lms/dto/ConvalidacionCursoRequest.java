package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConvalidacionCursoRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotBlank
    @Size(max = 150)
    private String institucionOrigen;

    @NotBlank
    @Size(max = 150)
    private String cursoOrigen;

    @NotNull
    private LocalDate fechaConvalidacion;
}
