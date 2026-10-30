package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.CampusRequest;
import pe.edu.upeu.lamb.institucional.dto.CampusResponse;
import pe.edu.upeu.lamb.institucional.entity.Campus;

@Component
public class CampusMapper {

    public Campus toEntity(CampusRequest request) {
        return Campus.builder()
                .nombre(request.getNombre())
                .idInstitucion(request.getIdInstitucion())
                .ciudad(request.getCiudad())
                .descripcion(request.getDescripcion())
                .build();
    }

    public CampusResponse toResponse(Campus entity) {
        return CampusResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idInstitucion(entity.getIdInstitucion())
                .ciudad(entity.getCiudad())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
