package pe.edu.upeu.lamb.trayectoria.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Evento postulante.admitido del topic admision-eventos, tal como lo ve trayectoria.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostulanteAdmitidoEvento {

    private String tipoEvento;
    private Long postulacionId;
    private Long idPersona;
    private Long idPrograma;
    private String periodo;
    private BigDecimal puntajeExamen;
    private String origen;
    private Long timestamp;
}
