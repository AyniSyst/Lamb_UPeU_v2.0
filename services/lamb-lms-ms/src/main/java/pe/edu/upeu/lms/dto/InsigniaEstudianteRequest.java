package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsigniaEstudianteRequest {

    @NotNull
    private Long insigniaId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;
}
