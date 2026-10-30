package pe.edu.upeu.lamb.curriculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// El curso se elige del catalogo (CRUD 12); el plan solo decide ciclo, tipo y area
@Getter
@Setter
public class PlanCursoRequest {

    @NotNull
    private Long idCursoCatalogo;

    @NotNull
    @Min(1)
    @Max(14)
    private Integer ciclo;

    @NotNull
    private Long idTipoCurso;

    private Long idAreaCurricular;
}
