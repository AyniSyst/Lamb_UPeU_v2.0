package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmbienteRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    private Long idPabellon;

    @NotNull
    private Long idSede;

    @Size(max = 50)
    private String tipoAmbiente;

    private Integer capacidad;
}
