package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoSolicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JustificacionInasistenciaResponse {
    private Long id;
    private Long idRegistro;
    private String motivo;
    private String documentoUrl;
    private Long idSolicitante;
    private EstadoSolicitud estado;
    private String observacionRespuesta;
    private Long idRespondidoPor;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
}
