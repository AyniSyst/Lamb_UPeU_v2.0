package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.AccionBitacora;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraAuditoriaPagoResponse {
    private Long id;
    private PagoResponse pago;
    private AccionBitacora accion;
    private String idUsuario;
    private LocalDateTime fecha;
    private String observaciones;
}
