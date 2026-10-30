package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ModalidadTitulacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.ModalidadTitulacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.ModalidadTitulacion;
import org.springframework.stereotype.Component;

@Component
public class ModalidadTitulacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ModalidadTitulacion toEntity(ModalidadTitulacionRequest request) {
        return ModalidadTitulacion.builder()
                .tipo(request.getTipo())
                .descripcion(request.getDescripcion())
                .vigente(request.getVigente())
                .build();
    }

    public ModalidadTitulacionResponse toResponse(ModalidadTitulacion modalidadTitulacion) {
        return ModalidadTitulacionResponse.builder()
                .id(modalidadTitulacion.getId())
                .idPlan(modalidadTitulacion.getPlan().getId())
                .versionPlan(modalidadTitulacion.getPlan().getVersion())
                .tipo(modalidadTitulacion.getTipo())
                .descripcion(modalidadTitulacion.getDescripcion())
                .vigente(modalidadTitulacion.getVigente())
                .build();
    }
}
