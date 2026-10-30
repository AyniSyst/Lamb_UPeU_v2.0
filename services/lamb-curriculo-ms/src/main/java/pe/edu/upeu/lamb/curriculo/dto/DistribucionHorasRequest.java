package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistribucionHorasRequest {

    @NotNull
    private Long idCurso;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer horasTeoria;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer horasPractica;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer horasLaboratorio;
}
