package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncuestaSatisfaccionRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer calificacion;

    @Size(max = 500)
    private String comentario;
}
