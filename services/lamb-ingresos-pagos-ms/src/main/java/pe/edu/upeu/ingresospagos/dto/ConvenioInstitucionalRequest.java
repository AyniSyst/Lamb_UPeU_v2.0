package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ConvenioInstitucionalRequest {

    @NotBlank
    @Size(max = 150)
    private String nombreEmpresa;

    @NotBlank
    @Size(max = 20)
    private String ruc;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0", inclusive = true)
    private BigDecimal porcentajeDescuento;

    @NotNull
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @NotNull
    private Boolean activo;
}
