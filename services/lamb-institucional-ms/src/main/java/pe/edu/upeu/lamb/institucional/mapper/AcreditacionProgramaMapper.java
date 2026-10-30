package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.AcreditacionProgramaRequest;
import pe.edu.upeu.lamb.institucional.dto.AcreditacionProgramaResponse;
import pe.edu.upeu.lamb.institucional.entity.AcreditacionPrograma;

@Component
public class AcreditacionProgramaMapper {

    public AcreditacionPrograma toEntity(AcreditacionProgramaRequest request) {
        return AcreditacionPrograma.builder()
                .idPrograma(request.getIdPrograma())
                .entidadAcreditadora(request.getEntidadAcreditadora())
                .fechaAcreditacion(request.getFechaAcreditacion())
                .fechaVigencia(request.getFechaVigencia())
                .build();
    }

    public AcreditacionProgramaResponse toResponse(AcreditacionPrograma entity) {
        return AcreditacionProgramaResponse.builder()
                .id(entity.getId())
                .idPrograma(entity.getIdPrograma())
                .entidadAcreditadora(entity.getEntidadAcreditadora())
                .fechaAcreditacion(entity.getFechaAcreditacion())
                .fechaVigencia(entity.getFechaVigencia())
                .build();
    }
}
