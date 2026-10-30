package pe.edu.upeu.lamb.planificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmbienteRequest {

    @NotNull
    private Long idPabellon;

    @NotNull
    private Long idTipoAmbiente;

    @NotBlank
    @Size(max = 20)
    private String codigo;

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotNull
    @Min(0)
    @Max(30)
    private Integer piso;

    @NotNull
    @Min(1)
    private Integer aforo;

    @NotNull
    private Boolean activo;
}
