package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.lms.entity.TipoPregunta;

import java.math.BigDecimal;

@Getter
@Setter
public class PreguntaBancoRequest {

    @NotBlank
    @Size(max = 500)
    private String enunciado;

    @NotNull
    private TipoPregunta tipo;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal puntajeSugerido;

    @Size(max = 100)
    private String categoria;

    @NotNull
    private Boolean activo;
}
