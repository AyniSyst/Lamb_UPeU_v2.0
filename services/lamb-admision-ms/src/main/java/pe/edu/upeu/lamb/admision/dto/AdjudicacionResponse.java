package pe.edu.upeu.lamb.admision.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdjudicacionResponse {
    private Long id;
    private Long idPostulante;
    private String codigoPostulante;
    private Long idModalidad;
    private String codigoModalidad;
    private Long idPrograma;
    private BigDecimal puntajeFinal;
    private Integer ordenMerito;
    private String estado;
    private String numeroResolucion;
    private Instant fechaAdjudicacion;
    private Instant fechaPublicacion;
    private String observacion;
    private Long version;
}
