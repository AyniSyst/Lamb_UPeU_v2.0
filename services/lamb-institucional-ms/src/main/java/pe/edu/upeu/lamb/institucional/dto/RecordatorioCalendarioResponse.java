package pe.edu.upeu.lamb.institucional.dto;

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
public class RecordatorioCalendarioResponse {
    private Long id;
    private Long idEventoCalendario;
    private String mensaje;
    private Instant fechaEnvio;
}
