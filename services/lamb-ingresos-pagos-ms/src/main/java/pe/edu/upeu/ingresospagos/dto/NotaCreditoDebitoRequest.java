package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.TipoNotaCreditoDebito;

import java.math.BigDecimal;

@Getter
@Setter
public class NotaCreditoDebitoRequest {

    @NotNull
    private Long pagoId;

    @NotNull
    private TipoNotaCreditoDebito tipo;

    @NotBlank
    @Size(max = 255)
    private String motivo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal monto;
}
