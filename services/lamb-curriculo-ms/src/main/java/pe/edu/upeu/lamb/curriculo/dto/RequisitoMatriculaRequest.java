package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.TipoRequisitoMatricula;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisitoMatriculaRequest {

    @NotNull
    private Long idPlanCurso;

    @NotNull
    private TipoRequisitoMatricula tipo;

    private Long idCursoRequisito;

    @Min(1)
    @Max(300)
    private Integer creditosMinimos;

    @Min(1)
    @Max(14)
    private Integer cicloMinimo;

    @Size(max = 255)
    private String descripcion;
}
