package pe.edu.upeu.lamb.planificacion.dto;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.planificacion.entity.TipoCierreFusion;
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
public class SolicitudCierreFusionGrupoResponse {
    private Long id;
    private TipoCierreFusion tipo;
    private Long idGrupo;
    private String codigoGrupo;
    private Long idGrupoDestino;
    private String codigoGrupoDestino;
    private String justificacion;
    private Long idSolicitante;
    private EstadoSolicitud estado;
    private String observacionRespuesta;
    private Long idRespondidoPor;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
}
