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
public class InhabilitacionInasistenciaResponse {
    private Long id;
    private Long idGrupo;
    private Long idPersona;
    private BigDecimal porcentajeInasistencia;
    private LocalDate fecha;
    private String observacion;
}
