package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialDocumentoIdentidadRequest {

    @NotNull
    private Long idPersona;

    @Size(max = 30)
    private String tipoCambio;

    @Size(max = 20)
    private String numeroAnterior;

    @NotBlank
    @Size(max = 20)
    private String numeroNuevo;
}
