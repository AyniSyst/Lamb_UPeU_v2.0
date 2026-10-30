package pe.edu.upeu.lms.dto;

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
public class SeccionCursoResponse {
    private Long id;
    private CursoResponse curso;
    private DocenteResponse docente;
    private PeriodoAcademicoResponse periodoAcademico;
    private String nombreSeccion;
    private Integer cupoMaximo;
    private Boolean activo;
}
