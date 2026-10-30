package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaVencimientoDocumentoResponse {

    private Long id;
    private Long idDocumentoPersona;
    private LocalDate fechaVigenciaDesde;
    private LocalDate fechaVencimiento;
    private Instant fechaRegistro;
}
