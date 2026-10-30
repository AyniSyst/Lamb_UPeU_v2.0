package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ModalidadRequest;
import pe.edu.upeu.lamb.curriculo.dto.ModalidadResponse;
import pe.edu.upeu.lamb.curriculo.entity.Modalidad;
import org.springframework.stereotype.Component;

@Component
public class ModalidadMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public Modalidad toEntity(ModalidadRequest request) {
        return Modalidad.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ModalidadResponse toResponse(Modalidad modalidad) {
        return ModalidadResponse.builder()
                .id(modalidad.getId())
                .codigo(modalidad.getCodigo())
                .nombre(modalidad.getNombre())
                .descripcion(modalidad.getDescripcion())
                .build();
    }
}
