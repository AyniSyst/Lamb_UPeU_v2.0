package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.TipoCalculoRecargo;

import java.math.BigDecimal;

@Getter
@Setter
public class RecargoMoraRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    private TipoCalculoRecargo tipoCalculo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer diasGracia;

    @NotNull
    private Boolean activo;
}
