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
public class ValidacionIdentidadResponse {
    private Long id;
    private Long idPersona;
    private String fuente;
    private String resultado;
    private String observacion;
    private Instant fechaValidacion;
}
