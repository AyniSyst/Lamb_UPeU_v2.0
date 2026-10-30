package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoAprendizajeRequest {

    @NotNull
    private Long idCurso;

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 500)
    private String descripcion;
}
