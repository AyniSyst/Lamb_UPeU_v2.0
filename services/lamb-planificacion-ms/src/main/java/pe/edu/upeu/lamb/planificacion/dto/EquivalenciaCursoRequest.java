package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquivalenciaCursoRequest {

    @NotNull
    private Long idCurso;

    @NotNull
    private Long idProgramaOrigen;

    @NotNull
    private Long idCursoEquivalente;

    @NotNull
    private Long idProgramaEquivalente;

    @Size(max = 255)
    private String observacion;
}
