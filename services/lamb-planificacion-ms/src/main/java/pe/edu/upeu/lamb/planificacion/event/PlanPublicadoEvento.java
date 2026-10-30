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
    // Datos del catalogo de cada curso: planificacion actualiza con ellos su copia local de cursos
    private List<CursoPublicado> cursos;
    private String origen;
    private Long timestamp;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CursoPublicado {
        private String codigo;
        private String nombre;
        private Integer creditos;
    }
}
