package pe.edu.upeu.lamb.evaluacion.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Eventos acta.abierta / acta.rechazada / acta.cerrada del topic evaluacion-eventos (key = grupoId).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaEvento {

    private String tipoEvento;
    private Long actaId;
    private Long grupoId;
    private String estado;
    private String motivo;
    private String codigoCurso;
    private String periodo;
    private Integer creditos;
    // Solo en acta.cerrada: nota final por estudiante
    private List<ResultadoNota> resultados;
    private String origen;
    private Long timestamp;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultadoNota {
        private Long idPersona;
        private BigDecimal notaFinal;
        private boolean aprobado;
    }
}
