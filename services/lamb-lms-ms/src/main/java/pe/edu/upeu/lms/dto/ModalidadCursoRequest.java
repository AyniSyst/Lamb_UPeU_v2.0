package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModalidadCursoRequest {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    private Boolean activo;
}
