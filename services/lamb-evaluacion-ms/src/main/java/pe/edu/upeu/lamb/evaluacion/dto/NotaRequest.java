package pe.edu.upeu.lamb.evaluacion.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NotaRequest {

    @NotNull
    private Long idPersona;

    @NotNull
    private Long idGrupo;

    @NotNull
    private Long idComponente;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "20.0")
    private BigDecimal calificacion;

    @NotBlank
    @Size(max = 20)
    private String periodo;
}
