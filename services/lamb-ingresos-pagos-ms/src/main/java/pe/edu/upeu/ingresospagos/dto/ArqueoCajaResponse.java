package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArqueoCajaResponse {
    private Long id;
    private SesionCajaResponse sesionCaja;
    private BigDecimal montoContado;
    private BigDecimal montoSistema;
    private BigDecimal diferencia;
    private String observaciones;
    private LocalDateTime fechaArqueo;
}
