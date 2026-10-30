package pe.edu.upeu.lms.dto;

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
public class TareaRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    private LocalDateTime fechaLimite;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal puntajeMaximo;

    @NotNull
    private Boolean activo;
}
