package pe.edu.upeu.lamb.institucional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioFacultadEscuelaResponse {
    private Long id;
    private Long idCalendarioAcademico;
    private Long idFacultad;
    private Long idEscuela;
}
