package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrerequisitoCursoRequest {

    @NotNull
    private Long cursoId;

    @NotNull
    private Long cursoPrerequisitoId;
}
