package pe.edu.upeu.lamb.admision.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdjudicacionRequest {
    @NotNull private Long idPostulante;
    @NotNull private Long idModalidad;
    @NotNull private Long idPrograma;
    @NotNull @DecimalMin("0.0") private BigDecimal puntajeFinal;
    @Min(1) private Integer ordenMerito;
    @Size(max = 50) private String numeroResolucion;
    @Size(max = 500) private String observacion;
}
