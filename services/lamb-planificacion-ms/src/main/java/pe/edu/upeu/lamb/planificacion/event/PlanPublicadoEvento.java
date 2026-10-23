package pe.edu.upeu.lamb.planificacion.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Evento plan.publicado del topic curriculo-eventos, tal como lo ve planificacion.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanPublicadoEvento {

    private String tipoEvento;
    private Long planId;
    private Long idPrograma;
    private String version;
    private Long idPlanDerogado;
    private List<String> codigosCursos;
    private String origen;
    private Long timestamp;
}
