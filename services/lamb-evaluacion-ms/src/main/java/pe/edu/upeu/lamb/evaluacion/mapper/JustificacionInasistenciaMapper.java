package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.JustificacionInasistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.JustificacionInasistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.JustificacionInasistencia;
import org.springframework.stereotype.Component;

@Component
public class JustificacionInasistenciaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public JustificacionInasistencia toEntity(JustificacionInasistenciaRequest request) {
        return JustificacionInasistencia.builder()
                .motivo(request.getMotivo().trim())
                .documentoUrl(request.getDocumentoUrl())
                .build();
    }

    public JustificacionInasistenciaResponse toResponse(JustificacionInasistencia justificacionInasistencia) {
        return JustificacionInasistenciaResponse.builder()
                .id(justificacionInasistencia.getId())
                .idRegistro(justificacionInasistencia.getRegistro().getId())
                .motivo(justificacionInasistencia.getMotivo())
                .documentoUrl(justificacionInasistencia.getDocumentoUrl())
                .idSolicitante(justificacionInasistencia.getIdSolicitante())
                .estado(justificacionInasistencia.getEstado())
                .observacionRespuesta(justificacionInasistencia.getObservacionRespuesta())
                .idRespondidoPor(justificacionInasistencia.getIdRespondidoPor())
                .fechaSolicitud(justificacionInasistencia.getFechaSolicitud())
                .fechaRespuesta(justificacionInasistencia.getFechaRespuesta())
                .build();
    }
}
