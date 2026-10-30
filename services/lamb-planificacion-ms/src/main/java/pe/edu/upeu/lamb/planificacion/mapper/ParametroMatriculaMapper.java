package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.ParametroMatriculaRequest;
import pe.edu.upeu.lamb.planificacion.dto.ParametroMatriculaResponse;
import pe.edu.upeu.lamb.planificacion.entity.ParametroMatricula;
import org.springframework.stereotype.Component;

@Component
public class ParametroMatriculaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ParametroMatricula toEntity(ParametroMatriculaRequest request) {
        return ParametroMatricula.builder()
                .idPrograma(request.getIdPrograma())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .fechaLimiteRetiro(request.getFechaLimiteRetiro())
                .permiteExtemporanea(request.getPermiteExtemporanea())
                .recargoExtemporanea(request.getRecargoExtemporanea())
                .build();
    }

    public ParametroMatriculaResponse toResponse(ParametroMatricula parametroMatricula) {
        return ParametroMatriculaResponse.builder()
                .id(parametroMatricula.getId())
                .idPeriodo(parametroMatricula.getPeriodo().getId())
                .codigoPeriodo(parametroMatricula.getPeriodo().getCodigo())
                .idPrograma(parametroMatricula.getIdPrograma())
                .fechaInicio(parametroMatricula.getFechaInicio())
                .fechaFin(parametroMatricula.getFechaFin())
                .fechaLimiteRetiro(parametroMatricula.getFechaLimiteRetiro())
                .permiteExtemporanea(parametroMatricula.getPermiteExtemporanea())
                .recargoExtemporanea(parametroMatricula.getRecargoExtemporanea())
                .build();
    }
}
