package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.UnidadAprendizajeRequest;
import pe.edu.upeu.lamb.curriculo.dto.UnidadAprendizajeResponse;
import pe.edu.upeu.lamb.curriculo.entity.UnidadAprendizaje;
import org.springframework.stereotype.Component;

@Component
public class UnidadAprendizajeMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public UnidadAprendizaje toEntity(UnidadAprendizajeRequest request) {
        return UnidadAprendizaje.builder()
                .numero(request.getNumero())
                .nombre(request.getNombre().trim())
                .semanaInicio(request.getSemanaInicio())
                .semanaFin(request.getSemanaFin())
                .contenidos(request.getContenidos())
                .build();
    }

    public UnidadAprendizajeResponse toResponse(UnidadAprendizaje unidadAprendizaje) {
        return UnidadAprendizajeResponse.builder()
                .id(unidadAprendizaje.getId())
                .idSilabo(unidadAprendizaje.getSilabo().getId())
                .versionSilabo(unidadAprendizaje.getSilabo().getVersion())
                .numero(unidadAprendizaje.getNumero())
                .nombre(unidadAprendizaje.getNombre())
                .semanaInicio(unidadAprendizaje.getSemanaInicio())
                .semanaFin(unidadAprendizaje.getSemanaFin())
                .contenidos(unidadAprendizaje.getContenidos())
                .build();
    }
}
