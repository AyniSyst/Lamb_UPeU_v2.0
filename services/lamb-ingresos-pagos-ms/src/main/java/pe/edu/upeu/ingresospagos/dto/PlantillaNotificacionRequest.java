package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upeu.ingresospagos.entity.CanalNotificacion;

@Getter
@Setter
public class PlantillaNotificacionRequest {

    @NotBlank
    @Size(max = 50)
    private String codigo;

    @NotBlank
    @Size(max = 150)
    private String asunto;

    @NotBlank
    @Size(max = 1000)
    private String cuerpo;

    @NotNull
    private CanalNotificacion canal;

    @NotNull
    private Boolean activo;
}
