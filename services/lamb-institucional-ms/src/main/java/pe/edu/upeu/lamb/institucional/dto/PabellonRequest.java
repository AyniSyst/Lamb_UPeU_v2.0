package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PabellonRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    private Long idSede;

    private Integer numeroPisos;

    @Size(max = 255)
    private String descripcion;
}
