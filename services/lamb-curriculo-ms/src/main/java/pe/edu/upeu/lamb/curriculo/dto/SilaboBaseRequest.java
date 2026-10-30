package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.EstadoSilabo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SilaboBaseRequest {

    @NotNull
    private Long idCurso;

    @NotBlank
    @Size(max = 20)
    private String version;

    @Size(max = 1000)
    private String competencias;

    @Size(max = 1000)
    private String metodologia;

    @Size(max = 1000)
    private String evaluacion;

    @Size(max = 2000)
    private String bibliografia;

    @NotNull
    private EstadoSilabo estado;
}
