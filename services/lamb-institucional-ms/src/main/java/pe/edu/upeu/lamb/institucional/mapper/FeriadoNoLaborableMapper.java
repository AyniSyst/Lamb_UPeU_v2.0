package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.FeriadoNoLaborableRequest;
import pe.edu.upeu.lamb.institucional.dto.FeriadoNoLaborableResponse;
import pe.edu.upeu.lamb.institucional.entity.FeriadoNoLaborable;

@Component
public class FeriadoNoLaborableMapper {

    public FeriadoNoLaborable toEntity(FeriadoNoLaborableRequest request) {
        return FeriadoNoLaborable.builder()
                .nombre(request.getNombre())
                .fecha(request.getFecha())
                .descripcion(request.getDescripcion())
                .build();
    }

    public FeriadoNoLaborableResponse toResponse(FeriadoNoLaborable entity) {
        return FeriadoNoLaborableResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .fecha(entity.getFecha())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
