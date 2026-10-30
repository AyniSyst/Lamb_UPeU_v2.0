package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaForoRequest {

    @NotNull
    private Long foroHiloId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotBlank
    @Size(max = 1000)
    private String mensaje;
}
