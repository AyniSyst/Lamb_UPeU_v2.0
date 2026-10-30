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
public class FusionPersonasResponse {
    private Long id;
    private Long idPersonaOrigen;
    private Long idPersonaDestino;
    private String motivo;
    private Instant fechaFusion;
}
