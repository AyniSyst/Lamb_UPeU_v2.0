package pe.edu.upeu.ingresospagos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagadorTerceroRequest {

    @NotBlank
    @Size(max = 36)
    private String idEstudiante;

    @NotBlank
    @Size(max = 100)
    private String nombres;

    @NotBlank
    @Size(max = 100)
    private String apellidos;

    @NotBlank
    @Size(max = 20)
    private String tipoDocumento;

    @NotBlank
    @Size(max = 20)
    private String numeroDocumento;

    @Size(max = 20)
    private String telefono;

    @Email
    @Size(max = 150)
    private String email;

    @NotNull
    private Boolean activo;
}
