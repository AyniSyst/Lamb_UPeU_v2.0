package pe.edu.upeu.lamb.planificacion.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Evento seccion.abierta del topic planificacion-eventos (key = seccionId). Lo consume evaluacion.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeccionAbiertaEvento {

    private String tipoEvento;
    private Long seccionId;
    private String codigoCurso;
    private String periodo;
    private String docente;
    private Integer cupoMaximo;
    private Integer creditos;
    private String origen;
    private Long timestamp;
}
