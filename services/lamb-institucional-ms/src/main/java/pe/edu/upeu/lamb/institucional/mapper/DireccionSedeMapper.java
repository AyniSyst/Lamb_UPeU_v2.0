package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.DireccionSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.DireccionSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.DireccionSede;

@Component
public class DireccionSedeMapper {

    public DireccionSede toEntity(DireccionSedeRequest request) {
        return DireccionSede.builder()
                .idSede(request.getIdSede())
                .idUbigeo(request.getIdUbigeo())
                .direccionDetalle(request.getDireccionDetalle())
                .referencia(request.getReferencia())
                .build();
    }

    public DireccionSedeResponse toResponse(DireccionSede entity) {
        return DireccionSedeResponse.builder()
                .id(entity.getId())
                .idSede(entity.getIdSede())
                .idUbigeo(entity.getIdUbigeo())
                .direccionDetalle(entity.getDireccionDetalle())
                .referencia(entity.getReferencia())
                .build();
    }
}
