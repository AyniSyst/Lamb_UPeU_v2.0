package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradoTituloRequest {

    @NotNull
    private Long idPrograma;

    @Size(max = 100)
    private String grado;

    @Size(max = 150)
    private String titulo;
}
