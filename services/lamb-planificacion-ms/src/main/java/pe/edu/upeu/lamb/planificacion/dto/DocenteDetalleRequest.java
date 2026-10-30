package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.RolDocente;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// Linea de detalle Grupo x Docente dentro de la apertura de un grupo
@Getter
@Setter
public class DocenteDetalleRequest {

    @NotNull
    private Long idDocente;

    @NotNull
    private RolDocente rol;

    @NotNull
    @Min(1)
    @Max(40)
    private Integer horasSemanales;
}
