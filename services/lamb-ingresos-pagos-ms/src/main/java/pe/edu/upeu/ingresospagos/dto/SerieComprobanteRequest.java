package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieComprobanteRequest {

    @NotNull
    private Long tipoComprobanteId;

    @NotBlank
    @Size(max = 10)
    private String serie;

    @NotNull
    @Min(0)
    private Integer correlativoActual;

    @NotNull
    private Boolean activo;
}
