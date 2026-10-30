package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaEsperaRequest {

    @NotNull
    private Long seccionCursoId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotNull
    @Min(1)
    private Integer posicion;
}
