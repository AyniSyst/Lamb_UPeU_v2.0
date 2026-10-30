package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.TipoNotaCreditoDebito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaCreditoDebitoResponse {
    private Long id;
    private PagoResponse pago;
    private TipoNotaCreditoDebito tipo;
    private String motivo;
    private BigDecimal monto;
    private LocalDateTime fechaEmision;
}
