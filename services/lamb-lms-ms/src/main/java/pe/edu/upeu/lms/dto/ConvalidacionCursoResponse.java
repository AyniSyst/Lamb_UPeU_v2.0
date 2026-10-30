package pe.edu.upeu.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.lms.entity.EstadoConvalidacion;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvalidacionCursoResponse {
    private Long id;
    private CursoResponse curso;
    private String idEstudiante;
    private String institucionOrigen;
    private String cursoOrigen;
    private LocalDate fechaConvalidacion;
    private EstadoConvalidacion estado;
}
