package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.EstadoDocumentoTipo;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDocumentoResponse {

    private Long id;
    private Long idDocumentoPersona;
    private EstadoDocumentoTipo estado;
    private Instant fechaRegistro;
}
