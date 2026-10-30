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
public class TarifarioProgramaRequest {

    @NotBlank
    @Size(max = 100)
    private String programa;

    @NotNull
    private Long conceptoPagoId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal monto;

    @NotNull
    private LocalDate vigenteDesde;

    private LocalDate vigenteHasta;

    @NotNull
    private Boolean activo;
}
