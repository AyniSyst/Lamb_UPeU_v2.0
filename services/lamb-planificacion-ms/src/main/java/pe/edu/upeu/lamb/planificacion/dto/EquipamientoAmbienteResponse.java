package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoEquipamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipamientoAmbienteResponse {
    private Long id;
    private Long idAmbiente;
    private String codigoAmbiente;
    private String nombre;
    private Integer cantidad;
    private String codigoPatrimonial;
    private EstadoEquipamiento estado;
}
