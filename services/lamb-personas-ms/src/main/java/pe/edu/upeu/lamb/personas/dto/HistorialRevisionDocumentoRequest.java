package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialRevisionDocumentoRequest {

    @NotNull
    private Long idDocumentoPersona;

    @Size(max = 255)
    private String comentario;
}
