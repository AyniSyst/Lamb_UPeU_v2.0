package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ResolucionProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.ResolucionProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.ResolucionPrograma;

@Component
public class ResolucionProgramaMapper {

    public ResolucionPrograma toEntity(ResolucionProgramaRequest request) {
        return ResolucionPrograma.builder()
                .idPrograma(request.getIdPrograma())
                .numeroResolucion(request.getNumeroResolucion())
                .tipoResolucion(request.getTipoResolucion())
                .fechaEmision(request.getFechaEmision())
                .build();
    }

    public ResolucionProgramaResponse toResponse(ResolucionPrograma entity) {
        return ResolucionProgramaResponse.builder()
                .id(entity.getId())
                .idPrograma(entity.getIdPrograma())
                .numeroResolucion(entity.getNumeroResolucion())
                .tipoResolucion(entity.getTipoResolucion())
                .fechaEmision(entity.getFechaEmision())
                .build();
    }
}
