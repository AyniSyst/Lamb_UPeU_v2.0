package pe.edu.upeu.lamb.planificacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoTallerResponse {
    private Long id;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Long idCurso;
    private String codigoCurso;
    private String codigo;
    private String nombre;
    private Integer cupoMaximo;
    private List<Long> idProgramas;
}
