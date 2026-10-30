package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnuncioCursoRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @NotBlank
    @Size(max = 1000)
    private String mensaje;
}
