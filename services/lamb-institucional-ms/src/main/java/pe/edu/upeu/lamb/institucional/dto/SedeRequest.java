package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SedeRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    private Long idCampus;

    @NotNull
    private Long idTipoSede;

    @Size(max = 255)
    private String direccion;
}
