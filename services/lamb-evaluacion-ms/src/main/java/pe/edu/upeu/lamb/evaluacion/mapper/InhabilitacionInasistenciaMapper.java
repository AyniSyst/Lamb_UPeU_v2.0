package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.InhabilitacionInasistenciaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.InhabilitacionInasistenciaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.InhabilitacionInasistencia;
import org.springframework.stereotype.Component;

@Component
public class InhabilitacionInasistenciaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public InhabilitacionInasistencia toEntity(InhabilitacionInasistenciaRequest request) {
        return InhabilitacionInasistencia.builder()
                .idGrupo(request.getIdGrupo())
                .idPersona(request.getIdPersona())
                .fecha(request.getFecha())
                .observacion(request.getObservacion())
                .build();
    }

    public InhabilitacionInasistenciaResponse toResponse(InhabilitacionInasistencia inhabilitacionInasistencia) {
        return InhabilitacionInasistenciaResponse.builder()
                .id(inhabilitacionInasistencia.getId())
                .idGrupo(inhabilitacionInasistencia.getIdGrupo())
                .idPersona(inhabilitacionInasistencia.getIdPersona())
                .porcentajeInasistencia(inhabilitacionInasistencia.getPorcentajeInasistencia())
                .fecha(inhabilitacionInasistencia.getFecha())
                .observacion(inhabilitacionInasistencia.getObservacion())
                .build();
    }
}
