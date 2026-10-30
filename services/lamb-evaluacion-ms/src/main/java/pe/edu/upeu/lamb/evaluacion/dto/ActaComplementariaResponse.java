package pe.edu.upeu.lamb.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaComplementariaResponse {
    private Long id;
    private Long idActa;
    private String codigoCursoActa;
    private Long idPersona;
    private BigDecimal notaSustitutorio;
    private LocalDate fecha;
    private String observacion;
}
