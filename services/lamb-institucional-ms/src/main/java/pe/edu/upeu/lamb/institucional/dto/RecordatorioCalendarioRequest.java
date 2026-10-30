package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RecordatorioCalendarioRequest {

    @NotNull
    private Long idEventoCalendario;

    private String mensaje;

    private Instant fechaEnvio;
}
