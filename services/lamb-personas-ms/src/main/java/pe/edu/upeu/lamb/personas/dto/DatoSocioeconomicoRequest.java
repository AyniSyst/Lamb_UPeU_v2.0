package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatoSocioeconomicoRequest {

    @NotNull
    private Long idPersona;

    @Size(max = 60)
    private String nivelSocioeconomico;

    @Size(max = 60)
    private String ingresoFamiliar;
}
