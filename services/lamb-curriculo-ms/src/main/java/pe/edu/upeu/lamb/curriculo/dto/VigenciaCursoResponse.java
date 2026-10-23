package pe.edu.upeu.lamb.curriculo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Respuesta para planificacion (Feign): si el curso esta en algun plan VIGENTE.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaCursoResponse {
    private String codigoCurso;
    private boolean enPlanVigente;
    private Long idPlan;
    private String version;
    private String nombreCurso;
    private Integer creditos;
}
