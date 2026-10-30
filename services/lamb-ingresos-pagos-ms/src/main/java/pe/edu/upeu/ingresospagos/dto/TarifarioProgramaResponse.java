package pe.edu.upeu.ingresospagos.dto;

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
public class TarifarioProgramaResponse {
    private Long id;
    private String programa;
    private ConceptoPagoResponse conceptoPago;
    private BigDecimal monto;
    private LocalDate vigenteDesde;
    private LocalDate vigenteHasta;
    private Boolean activo;
}
