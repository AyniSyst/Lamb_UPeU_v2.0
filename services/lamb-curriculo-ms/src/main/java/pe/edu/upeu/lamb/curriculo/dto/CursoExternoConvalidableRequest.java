package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CursoExternoConvalidableRequest {

    @NotNull
    private Long idTabla;

    @NotBlank
    @Size(max = 20)
    private String codigoExterno;

    @NotBlank
    @Size(max = 200)
    private String nombreExterno;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer creditosExterno;

    @NotNull
    private Long idPlanCurso;

    @DecimalMin("0.0")
    private BigDecimal notaMinima;
}
