package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaEncuestaRequest {

    @NotNull
    private Long idPregunta;

    @NotNull
    private Long idGrupo;

    @NotNull
    private Long idDocente;

    @Min(0)
    @Max(5)
    private Integer valorEscala;

    @Size(max = 1000)
    private String valorTexto;
}
