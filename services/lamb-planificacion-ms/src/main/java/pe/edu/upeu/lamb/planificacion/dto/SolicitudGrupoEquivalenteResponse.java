package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
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
public class SolicitudGrupoEquivalenteResponse {
    private Long id;
    private Long idGrupoHabilitado;
    private String justificacion;
    private Long idSolicitante;
    private EstadoSolicitud estado;
    private String observacionRespuesta;
    private Long idRespondidoPor;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
}
