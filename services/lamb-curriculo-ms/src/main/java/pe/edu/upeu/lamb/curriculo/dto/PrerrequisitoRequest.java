package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrerrequisitoRequest {

    @NotNull
    private Long idPlanCursoRequisito;
}
