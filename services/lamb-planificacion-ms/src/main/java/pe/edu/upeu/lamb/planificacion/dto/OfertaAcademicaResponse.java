package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoOferta;
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
public class OfertaAcademicaResponse {
    private Long id;
    private Long idPeriodo;
    private String codigoPeriodo;
    private Long idPrograma;
    private Long idCurso;
    private String codigoCurso;
    private Integer ciclo;
    private EstadoOferta estado;
}
