package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SumillaCursoRequest {

    @NotNull
    private Long idCurso;

    @NotBlank
    @Size(max = 100)
    private String naturaleza;

    @NotBlank
    @Size(max = 1000)
    private String proposito;

    @NotBlank
    @Size(max = 2000)
    private String contenido;
}
