package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VigenciaVencimientoDocumentoRequest {

    @NotNull
    private Long idDocumentoPersona;

    private LocalDate fechaVigenciaDesde;

    private LocalDate fechaVencimiento;
}
