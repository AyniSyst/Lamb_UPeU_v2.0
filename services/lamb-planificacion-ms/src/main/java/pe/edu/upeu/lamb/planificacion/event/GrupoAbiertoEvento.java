package pe.edu.upeu.lamb.planificacion.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Evento grupo.abierto del topic planificacion-eventos (key = grupoId). Lo consume evaluacion.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoAbiertoEvento {

    private String tipoEvento;
    private Long grupoId;
    private String codigoGrupo;
    private String codigoCurso;
    private String periodo;
    // null si el grupo se abrio sin docente TITULAR: evaluacion rechaza el acta
    private Long idDocenteTitular;
    private Integer cupoMaximo;
    private Integer creditos;
    private String origen;
    private Long timestamp;
}
