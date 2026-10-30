package pe.edu.upeu.lamb.institucional.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarioFacultadEscuelaRequest {

    @NotNull
    private Long idCalendarioAcademico;

    private Long idFacultad;

    private Long idEscuela;
}
