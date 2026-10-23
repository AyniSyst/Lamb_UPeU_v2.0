package pe.edu.upeu.lamb.curriculo.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Evento plan.publicado del topic curriculo-eventos. Lo consume planificacion para
 * revalidar las secciones que quedaron PENDIENTE_VALIDACION.
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
