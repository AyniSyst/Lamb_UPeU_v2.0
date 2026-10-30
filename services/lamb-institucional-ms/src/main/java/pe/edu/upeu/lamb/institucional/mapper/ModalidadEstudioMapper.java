package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ModalidadEstudioRequest;
import pe.edu.upeu.lamb.institucional.dto.ModalidadEstudioResponse;
import pe.edu.upeu.lamb.institucional.entity.ModalidadEstudio;

@Component
public class ModalidadEstudioMapper {

    public ModalidadEstudio toEntity(ModalidadEstudioRequest request) {
        return ModalidadEstudio.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ModalidadEstudioResponse toResponse(ModalidadEstudio entity) {
        return ModalidadEstudioResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
