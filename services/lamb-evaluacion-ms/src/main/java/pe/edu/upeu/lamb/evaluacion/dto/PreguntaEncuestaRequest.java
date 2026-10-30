package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.TipoPregunta;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaEncuestaRequest {

    @NotNull
    private Long idEncuesta;

    @NotNull
    @Min(1)
    @Max(50)
    private Integer orden;

    @NotBlank
    @Size(max = 500)
    private String enunciado;

    @NotNull
    private TipoPregunta tipo;
}
