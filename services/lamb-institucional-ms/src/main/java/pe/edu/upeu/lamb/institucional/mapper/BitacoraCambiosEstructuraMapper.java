package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.BitacoraCambiosEstructuraRequest;
import pe.edu.upeu.lamb.institucional.dto.BitacoraCambiosEstructuraResponse;
import pe.edu.upeu.lamb.institucional.entity.BitacoraCambiosEstructura;

@Component
public class BitacoraCambiosEstructuraMapper {

    public BitacoraCambiosEstructura toEntity(BitacoraCambiosEstructuraRequest request) {
        return BitacoraCambiosEstructura.builder()
                .entidadAfectada(request.getEntidadAfectada())
                .idEntidadAfectada(request.getIdEntidadAfectada())
                .accion(request.getAccion())
                .detalle(request.getDetalle())
                .fechaCambio(request.getFechaCambio())
                .build();
    }

    public BitacoraCambiosEstructuraResponse toResponse(BitacoraCambiosEstructura entity) {
        return BitacoraCambiosEstructuraResponse.builder()
                .id(entity.getId())
                .entidadAfectada(entity.getEntidadAfectada())
                .idEntidadAfectada(entity.getIdEntidadAfectada())
                .accion(entity.getAccion())
                .detalle(entity.getDetalle())
                .fechaCambio(entity.getFechaCambio())
                .build();
    }
}
