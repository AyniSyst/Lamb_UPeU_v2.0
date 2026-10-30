package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForoHiloRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @NotBlank
    @Size(max = 36)
    private String idEstudianteCreador;
}
