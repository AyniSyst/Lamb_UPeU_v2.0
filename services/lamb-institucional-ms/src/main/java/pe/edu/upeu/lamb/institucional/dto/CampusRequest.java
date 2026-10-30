package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampusRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    private Long idInstitucion;

    @Size(max = 100)
    private String ciudad;

    @Size(max = 255)
    private String descripcion;
}
