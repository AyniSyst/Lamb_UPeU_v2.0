package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CalificacionRequest {

    @NotNull
    private Long evaluacionId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal nota;
}
