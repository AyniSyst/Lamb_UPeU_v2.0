package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogTransaccionPasarelaResponse {
    private Long id;
    private PagoResponse pago;
    private String proveedor;
    private String referenciaExterna;
    private String estado;
    private String payloadRespuesta;
    private LocalDateTime fecha;
}
