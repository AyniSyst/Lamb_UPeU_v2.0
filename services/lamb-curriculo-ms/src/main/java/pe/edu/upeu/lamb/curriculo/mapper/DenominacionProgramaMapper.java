package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.DenominacionProgramaRequest;
import pe.edu.upeu.lamb.curriculo.dto.DenominacionProgramaResponse;
import pe.edu.upeu.lamb.curriculo.entity.DenominacionPrograma;
import org.springframework.stereotype.Component;

@Component
public class DenominacionProgramaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public DenominacionPrograma toEntity(DenominacionProgramaRequest request) {
        return DenominacionPrograma.builder()
                .idPrograma(request.getIdPrograma())
                .denominacion(request.getDenominacion().trim())
                .duracionSemestres(request.getDuracionSemestres())
                .resolucion(request.getResolucion())
                .build();
    }

    public DenominacionProgramaResponse toResponse(DenominacionPrograma denominacionPrograma) {
        return DenominacionProgramaResponse.builder()
                .id(denominacionPrograma.getId())
                .idPrograma(denominacionPrograma.getIdPrograma())
                .denominacion(denominacionPrograma.getDenominacion())
                .idModalidad(denominacionPrograma.getModalidad().getId())
                .codigoModalidad(denominacionPrograma.getModalidad().getCodigo())
                .duracionSemestres(denominacionPrograma.getDuracionSemestres())
                .resolucion(denominacionPrograma.getResolucion())
                .build();
    }
}
