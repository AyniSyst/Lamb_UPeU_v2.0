package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoPersonaRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idTipoDocumento;

    @NotBlank
    @Size(max = 255)
    private String urlArchivo;
}
