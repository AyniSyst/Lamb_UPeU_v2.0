package pe.edu.upeu.lamb.personas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoPersonalResponse {
    private Long id;
    private Long idPersona;
    private LocalDate fechaNacimiento;
    private String lugarNacimiento;
    private Long idSexo;
    private Long idEstadoCivil;
    private Long idNacionalidad;
    private Long idUbigeoNacimiento;
}
