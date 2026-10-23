package pe.edu.upeu.lamb.planificacion.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Eventos acta.abierta / acta.rechazada del topic evaluacion-eventos, tal como los ve planificacion.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaEvento {

    private String tipoEvento;
    private Long actaId;
    private Long seccionId;
    private String estado;
    private String motivo;
    private String origen;
    private Long timestamp;
}
