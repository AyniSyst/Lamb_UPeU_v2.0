package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FinanciamientoEducativoRequest {

    @NotNull
    private Long entidadFinancieraId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal montoFinanciado;

    @NotNull
    @Min(1)
    private Integer numeroCuotas;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private Boolean activo;
}
