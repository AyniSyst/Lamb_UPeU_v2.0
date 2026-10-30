package pe.edu.upeu.lamb.curriculo.dto;

import pe.edu.upeu.lamb.curriculo.entity.EstadoSolicitud;
import pe.edu.upeu.lamb.curriculo.entity.TipoModificacionCurricular;
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
public class SolicitudModificacionCurricularResponse {
    private Long id;
    private Long idPlan;
    private String versionPlan;
    private TipoModificacionCurricular tipo;
    private String descripcion;
    private String justificacion;
    private Long idSolicitante;
    private EstadoSolicitud estado;
    private String observacionRespuesta;
    private Long idRespondidoPor;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
}
