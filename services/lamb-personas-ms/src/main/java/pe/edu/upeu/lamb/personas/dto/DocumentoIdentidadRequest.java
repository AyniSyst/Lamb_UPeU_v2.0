package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoIdentidadRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idTipoDocumentoIdentidad;

    @NotBlank
    @Size(max = 20)
    private String numero;
}
