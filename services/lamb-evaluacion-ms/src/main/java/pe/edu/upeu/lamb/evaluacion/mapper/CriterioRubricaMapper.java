package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.CriterioRubricaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.CriterioRubricaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.CriterioRubrica;
import org.springframework.stereotype.Component;

@Component
public class CriterioRubricaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CriterioRubrica toEntity(CriterioRubricaRequest request) {
        return CriterioRubrica.builder()
                .orden(request.getOrden())
                .descripcion(request.getDescripcion().trim())
                .puntajeMaximo(request.getPuntajeMaximo())
                .build();
    }

    public CriterioRubricaResponse toResponse(CriterioRubrica criterioRubrica) {
        return CriterioRubricaResponse.builder()
                .id(criterioRubrica.getId())
                .idRubrica(criterioRubrica.getRubrica().getId())
                .nombreRubrica(criterioRubrica.getRubrica().getNombre())
                .orden(criterioRubrica.getOrden())
                .descripcion(criterioRubrica.getDescripcion())
                .puntajeMaximo(criterioRubrica.getPuntajeMaximo())
                .build();
    }
}
