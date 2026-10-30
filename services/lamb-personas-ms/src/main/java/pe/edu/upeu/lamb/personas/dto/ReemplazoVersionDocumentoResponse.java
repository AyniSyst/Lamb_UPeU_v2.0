package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReemplazoVersionDocumentoResponse {

    private Long id;
    private Long idDocumentoPersona;
    private Long idDocumentoPersonaAnterior;
    private String motivo;
    private Instant fechaRegistro;
}
