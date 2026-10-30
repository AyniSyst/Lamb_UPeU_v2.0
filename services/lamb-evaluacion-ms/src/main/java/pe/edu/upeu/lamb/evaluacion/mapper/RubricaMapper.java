package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.RubricaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RubricaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.Rubrica;
import org.springframework.stereotype.Component;

@Component
public class RubricaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public Rubrica toEntity(RubricaRequest request) {
        return Rubrica.builder()
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public RubricaResponse toResponse(Rubrica rubrica) {
        return RubricaResponse.builder()
                .id(rubrica.getId())
                .idComponente(rubrica.getComponente().getId())
                .codigoComponente(rubrica.getComponente().getCodigo())
                .nombre(rubrica.getNombre())
                .descripcion(rubrica.getDescripcion())
                .build();
    }
}
