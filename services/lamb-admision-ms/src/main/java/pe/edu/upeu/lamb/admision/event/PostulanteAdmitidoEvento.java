package pe.edu.upeu.lamb.admision.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Evento postulante.admitido del topic admision-eventos (key = idPersona). Lo consume trayectoria
 * para abrir la trayectoria academica del nuevo estudiante.
 */
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
