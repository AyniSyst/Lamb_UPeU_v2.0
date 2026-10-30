package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ComisionCurricularRequest;
import pe.edu.upeu.lamb.curriculo.dto.ComisionCurricularResponse;
import pe.edu.upeu.lamb.curriculo.entity.ComisionCurricular;
import org.springframework.stereotype.Component;

@Component
public class ComisionCurricularMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ComisionCurricular toEntity(ComisionCurricularRequest request) {
        return ComisionCurricular.builder()
                .idPrograma(request.getIdPrograma())
                .nombre(request.getNombre().trim())
                .resolucion(request.getResolucion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .activa(request.getActiva())
                .build();
    }

    public ComisionCurricularResponse toResponse(ComisionCurricular comisionCurricular) {
        return ComisionCurricularResponse.builder()
                .id(comisionCurricular.getId())
                .idPrograma(comisionCurricular.getIdPrograma())
                .nombre(comisionCurricular.getNombre())
                .resolucion(comisionCurricular.getResolucion())
                .fechaInicio(comisionCurricular.getFechaInicio())
                .fechaFin(comisionCurricular.getFechaFin())
                .activa(comisionCurricular.getActiva())
                .build();
    }
}
