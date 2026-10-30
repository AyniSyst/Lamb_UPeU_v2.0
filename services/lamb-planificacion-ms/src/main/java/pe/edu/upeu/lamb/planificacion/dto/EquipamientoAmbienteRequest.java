package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoEquipamiento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamientoAmbienteRequest {

    @NotNull
    private Long idAmbiente;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    @Min(1)
    private Integer cantidad;

    @Size(max = 30)
    private String codigoPatrimonial;

    @NotNull
    private EstadoEquipamiento estado;
}
