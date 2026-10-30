package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionRequest {
    @NotNull private Long idExamen;
    @NotNull private Long idPostulante;
    @NotNull @DecimalMin("0.0") private BigDecimal puntajeTotal;
    private BigDecimal puntajeAptitud;
    private BigDecimal puntajeConocimiento;
    @Min(0) private Integer respuestasCorrectas;
    @Min(0) private Integer respuestasIncorrectas;
    @Min(0) private Integer respuestasBlanco;
    private Long idSupervisor;
    private Long idEncargado;
    @Size(max = 500) private String observacion;
}
