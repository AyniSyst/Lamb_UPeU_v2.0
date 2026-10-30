package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TipoCambioRequest {

    @NotNull
    private LocalDate fecha;

    @NotBlank
    @Size(max = 3)
    private String monedaOrigen;

    @NotBlank
    @Size(max = 3)
    private String monedaDestino;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;
}
