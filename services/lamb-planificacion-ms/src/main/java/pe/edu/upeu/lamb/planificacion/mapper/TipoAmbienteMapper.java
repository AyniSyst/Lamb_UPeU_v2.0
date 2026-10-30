package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.TipoAmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.TipoAmbienteResponse;
import pe.edu.upeu.lamb.planificacion.entity.TipoAmbiente;
import org.springframework.stereotype.Component;

@Component
public class TipoAmbienteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public TipoAmbiente toEntity(TipoAmbienteRequest request) {
        return TipoAmbiente.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public TipoAmbienteResponse toResponse(TipoAmbiente tipoAmbiente) {
        return TipoAmbienteResponse.builder()
                .id(tipoAmbiente.getId())
                .codigo(tipoAmbiente.getCodigo())
                .nombre(tipoAmbiente.getNombre())
                .descripcion(tipoAmbiente.getDescripcion())
                .build();
    }
}
