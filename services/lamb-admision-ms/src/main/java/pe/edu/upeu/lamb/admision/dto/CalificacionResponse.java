package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionResponse {
    private Long id;
    private Long idExamen;
    private String codigoExamen;
    private Long idPostulante;
    private String codigoPostulante;
    private BigDecimal puntajeTotal;
    private BigDecimal puntajeAptitud;
    private BigDecimal puntajeConocimiento;
    private Integer respuestasCorrectas;
    private Integer respuestasIncorrectas;
    private Integer respuestasBlanco;
    private Integer ordenMerito;
    private Long idSupervisor;
    private Long idEncargado;
    private String observacion;
    private Instant fechaCalificacion;
}
