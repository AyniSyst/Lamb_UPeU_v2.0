package pe.edu.upeu.lamb.institucional.dto;

import pe.edu.upeu.lamb.institucional.entity.GradoAcademico;
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
public class ProgramaResponse {
    private Long id;
    private String nombre;
    private GradoAcademico gradoAcademico;
    private Integer duracionSemestres;
    private FacultadResponse facultad;
}
