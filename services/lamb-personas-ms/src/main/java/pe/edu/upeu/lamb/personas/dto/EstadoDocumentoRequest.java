package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.EstadoDocumentoTipo;

@Getter
@Setter
public class EstadoDocumentoRequest {

    @NotNull
    private Long idDocumentoPersona;

    @NotNull
    private EstadoDocumentoTipo estado;
}
