package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoHabilitadoEquivalenciaRequest {

    @NotNull
    private Long idEquivalencia;

    @NotNull
    private Long idGrupo;

    @NotNull
    @Min(1)
    private Integer cuposHabilitados;
}
