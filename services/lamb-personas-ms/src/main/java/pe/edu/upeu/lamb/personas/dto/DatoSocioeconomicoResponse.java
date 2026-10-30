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
public class DatoSocioeconomicoResponse {
    private Long id;
    private Long idPersona;
    private String nivelSocioeconomico;
    private String ingresoFamiliar;
    private Instant fechaRegistro;
}
