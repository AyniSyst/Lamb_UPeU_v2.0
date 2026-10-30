package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoDocumentarioRequest {

    @NotNull
    private Long idTipoDocumento;

    @Size(max = 255)
    private String descripcion;

    private Boolean obligatorio;
}
