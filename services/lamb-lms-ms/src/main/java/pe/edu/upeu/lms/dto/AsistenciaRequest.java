package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsistenciaRequest {

    @NotNull
    private Long sesionClaseId;

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotNull
    private Boolean presente;
}
