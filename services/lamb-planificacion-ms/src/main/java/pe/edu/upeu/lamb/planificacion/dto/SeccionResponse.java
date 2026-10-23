package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSeccion;
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
    private EstadoSeccion estado;
    private String motivo;
    private CursoResponse curso;
}
