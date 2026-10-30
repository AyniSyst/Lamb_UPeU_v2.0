package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SesionCajaRequest {

    @NotNull
    private Long cajaId;

    @NotBlank
    @Size(max = 36)
    private String idCajero;

    @NotNull
    private LocalDateTime fechaApertura;

    private LocalDateTime fechaCierre;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal montoInicial;

    private BigDecimal montoFinal;
}
