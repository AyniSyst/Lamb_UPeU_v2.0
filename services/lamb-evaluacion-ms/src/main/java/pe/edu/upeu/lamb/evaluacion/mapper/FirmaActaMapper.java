package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.FirmaActaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.FirmaActaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.FirmaActa;
import org.springframework.stereotype.Component;

@Component
public class FirmaActaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public FirmaActa toEntity(FirmaActaRequest request) {
        return FirmaActa.builder()
                .idPersona(request.getIdPersona())
                .rol(request.getRol())
                .fecha(request.getFecha())
                .build();
    }

    public FirmaActaResponse toResponse(FirmaActa firmaActa) {
        return FirmaActaResponse.builder()
                .id(firmaActa.getId())
                .idActa(firmaActa.getActa().getId())
                .codigoCursoActa(firmaActa.getActa().getCodigoCurso())
                .idPersona(firmaActa.getIdPersona())
                .rol(firmaActa.getRol())
                .fecha(firmaActa.getFecha())
                .build();
    }
}
