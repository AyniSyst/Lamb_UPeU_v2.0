package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirmaValidacionDocumentoRequest {

    @NotNull
    private Long idDocumentoPersona;

    @Size(max = 150)
    private String firmante;
}
