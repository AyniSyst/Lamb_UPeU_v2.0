package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.TituloProfesionalRequest;
import pe.edu.upeu.lamb.curriculo.dto.TituloProfesionalResponse;
import pe.edu.upeu.lamb.curriculo.entity.TituloProfesional;
import org.springframework.stereotype.Component;

@Component
public class TituloProfesionalMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public TituloProfesional toEntity(TituloProfesionalRequest request) {
        return TituloProfesional.builder()
                .idPrograma(request.getIdPrograma())
                .denominacion(request.getDenominacion().trim())
                .resolucion(request.getResolucion())
                .build();
    }

    public TituloProfesionalResponse toResponse(TituloProfesional tituloProfesional) {
        return TituloProfesionalResponse.builder()
                .id(tituloProfesional.getId())
                .idPrograma(tituloProfesional.getIdPrograma())
                .denominacion(tituloProfesional.getDenominacion())
                .resolucion(tituloProfesional.getResolucion())
                .build();
    }
}
