package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoInvestigacionRequest {

    @NotNull
    private Long idLinea;

    @NotNull
    private Long idPlanCurso;
}
