package pe.edu.upeu.ingresospagos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.CanalNotificacion;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaNotificacionResponse {
    private Long id;
    private String codigo;
    private String asunto;
    private String cuerpo;
    private CanalNotificacion canal;
    private Boolean activo;
}
