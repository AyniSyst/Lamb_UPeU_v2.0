package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TituloProfesionalRequest {

    @NotNull
    private Long idPrograma;

    @NotBlank
    @Size(max = 200)
    private String denominacion;

    @Size(max = 50)
    private String resolucion;
}
