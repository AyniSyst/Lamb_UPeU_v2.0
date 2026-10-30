package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.PersonaEstado;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoPersonaResponse {
    private Long id;
    private Long idPersona;
    private PersonaEstado estado;
    private String motivo;
    private Instant fechaRegistro;
}
