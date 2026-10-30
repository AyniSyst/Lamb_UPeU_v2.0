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
public class CorreccionIdentificacionResponse {
    private Long id;
    private Long idPersona;
    private String campoCorregido;
    private String valorAnterior;
    private String valorNuevo;
    private String sustento;
    private Instant fechaCorreccion;
}
