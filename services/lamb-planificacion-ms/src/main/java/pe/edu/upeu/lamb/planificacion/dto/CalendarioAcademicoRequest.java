package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoCalendario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarioAcademicoRequest {

    @NotNull
    private Long idPeriodo;

    @NotNull
    private Long idSede;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @Size(max = 50)
    private String resolucion;

    @NotNull
    private EstadoCalendario estado;
}
