package pe.edu.upeu.lamb.trayectoria.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Eventos del topic evaluacion-eventos, tal como los ve trayectoria: solo le interesa acta.cerrada.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaCerradaEvento {

    private String tipoEvento;
    private Long actaId;
    private Long grupoId;
    private String codigoCurso;
    private String periodo;
    private Integer creditos;
    private List<ResultadoNota> resultados;
    private String origen;
    private Long timestamp;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultadoNota {
        private Long idPersona;
        private BigDecimal notaFinal;
        private boolean aprobado;
    }
}
