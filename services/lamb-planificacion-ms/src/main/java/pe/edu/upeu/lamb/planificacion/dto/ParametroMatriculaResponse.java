package pe.edu.upeu.lamb.planificacion.dto;

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
public class ParametroMatriculaResponse {
    private Long id;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Long idPrograma;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaLimiteRetiro;
    private Boolean permiteExtemporanea;
    private BigDecimal recargoExtemporanea;
}
