package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.UniversidadOrigenRequest;
import pe.edu.upeu.lamb.curriculo.dto.UniversidadOrigenResponse;
import pe.edu.upeu.lamb.curriculo.entity.UniversidadOrigen;
import org.springframework.stereotype.Component;

@Component
public class UniversidadOrigenMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public UniversidadOrigen toEntity(UniversidadOrigenRequest request) {
        return UniversidadOrigen.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .pais(request.getPais().trim())
                .tipo(request.getTipo())
                .build();
    }

    public UniversidadOrigenResponse toResponse(UniversidadOrigen universidadOrigen) {
        return UniversidadOrigenResponse.builder()
                .id(universidadOrigen.getId())
                .codigo(universidadOrigen.getCodigo())
                .nombre(universidadOrigen.getNombre())
                .pais(universidadOrigen.getPais())
                .tipo(universidadOrigen.getTipo())
                .build();
    }
}
