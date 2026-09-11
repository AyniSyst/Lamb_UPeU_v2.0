package pe.edu.upeu.lamb.institucional.dto;

import pe.edu.upeu.lamb.institucional.entity.GradoAcademico;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramaRequest {

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    private GradoAcademico gradoAcademico;

    @NotNull
    @Min(1)
    private Integer duracionSemestres;

    @NotNull
    private Long facultadId;
}
