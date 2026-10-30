package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DenominacionProgramaRequest {

    @NotNull
    private Long idPrograma;

    @NotBlank
    @Size(max = 200)
    private String denominacion;

    @NotNull
    private Long idModalidad;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer duracionSemestres;

    @Size(max = 50)
    private String resolucion;
}
