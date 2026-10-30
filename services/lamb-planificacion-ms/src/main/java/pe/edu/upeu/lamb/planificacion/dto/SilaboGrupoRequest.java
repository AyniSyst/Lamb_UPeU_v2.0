package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSilabo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SilaboGrupoRequest {

    @NotNull
    private Long idGrupo;

    private Long idSilaboBase;

    @Size(max = 1000)
    private String metodologia;

    @NotNull
    private EstadoSilabo estado;

    private LocalDate fechaPresentacion;
}
