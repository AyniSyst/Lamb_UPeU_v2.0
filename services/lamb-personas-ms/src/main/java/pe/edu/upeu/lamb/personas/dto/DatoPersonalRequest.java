package pe.edu.upeu.lamb.personas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DatoPersonalRequest {

    @NotNull
    private Long idPersona;

    private LocalDate fechaNacimiento;

    private String lugarNacimiento;

    private Long idSexo;

    private Long idEstadoCivil;

    private Long idNacionalidad;

    private Long idUbigeoNacimiento;
}
