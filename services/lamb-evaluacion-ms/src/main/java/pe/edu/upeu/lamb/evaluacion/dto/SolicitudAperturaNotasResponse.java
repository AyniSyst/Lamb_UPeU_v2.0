package pe.edu.upeu.lamb.evaluacion.dto;

import pe.edu.upeu.lamb.evaluacion.entity.EstadoSolicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAperturaNotasResponse {
    private Long id;
    private Long idGrupo;
    private String motivo;
    private LocalDate fechaInicioPropuesta;
    private LocalDate fechaFinPropuesta;
    private Long idSolicitante;
    private EstadoSolicitud estado;
    private String observacionRespuesta;
    private Long idRespondidoPor;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
}
