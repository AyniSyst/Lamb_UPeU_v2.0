package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lamb.personas.entity.TipoBaja;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BajaAnonimizacionResponse {
    private Long id;
    private Long idPersona;
    private TipoBaja tipo;
    private String motivo;
    private Instant fechaAplicacion;
}
