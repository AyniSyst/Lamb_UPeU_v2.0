package pe.edu.upeu.lamb.personas.dto;

import pe.edu.upeu.lamb.personas.entity.TipoPersona;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonaRequest {

    @NotBlank
    @Size(max = 20)
    private String tipoDocumento;

    @NotBlank
    @Size(max = 20)
    private String numeroDocumento;

    @NotBlank
    @Size(max = 100)
    private String nombres;

    @NotBlank
    @Size(max = 100)
    private String apellidos;

    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 20)
    private String telefono;

    private LocalDate fechaNacimiento;

    @NotNull
    private TipoPersona tipo;
}
