package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.TipoInstitucionRequest;
import pe.edu.upeu.lamb.institucional.dto.TipoInstitucionResponse;
import pe.edu.upeu.lamb.institucional.entity.TipoInstitucion;

@Component
public class TipoInstitucionMapper {

    public TipoInstitucion toEntity(TipoInstitucionRequest request) {
        return TipoInstitucion.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoInstitucionResponse toResponse(TipoInstitucion entity) {
        return TipoInstitucionResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
