package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.EstadoSesionCaja;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionCajaResponse {
    private Long id;
    private CajaResponse caja;
    private String idCajero;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private BigDecimal montoInicial;
    private BigDecimal montoFinal;
    private EstadoSesionCaja estado;
}
