package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BecaAsignadaRequest {

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotNull
    private Long becaId;

    @NotNull
    private Boolean activo;
}
