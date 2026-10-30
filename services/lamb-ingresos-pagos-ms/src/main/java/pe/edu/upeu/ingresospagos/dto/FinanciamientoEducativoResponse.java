package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.EstadoFinanciamiento;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanciamientoEducativoResponse {
    private Long id;
    private EntidadFinancieraResponse entidadFinanciera;
    private String idEstudiante;
    private BigDecimal montoFinanciado;
    private Integer numeroCuotas;
    private LocalDate fechaInicio;
    private EstadoFinanciamiento estado;
    private Boolean activo;
}
