package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ArqueoCajaRequest {

    @NotNull
    private Long sesionCajaId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal montoContado;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal montoSistema;

    @Size(max = 255)
    private String observaciones;
}
