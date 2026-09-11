package pe.edu.upeu.lamb.planificacion.dto;

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
public class SeccionResponse {
    private Long id;
    private String periodo;
    private String docente;
    private Integer cupoMaximo;
    private String horario;
    private CursoResponse curso;
}
