package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.CompetenciaEspecificaRequest;
import pe.edu.upeu.lamb.curriculo.dto.CompetenciaEspecificaResponse;
import pe.edu.upeu.lamb.curriculo.entity.CompetenciaEspecifica;
import org.springframework.stereotype.Component;

@Component
public class CompetenciaEspecificaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public CompetenciaEspecifica toEntity(CompetenciaEspecificaRequest request) {
        return CompetenciaEspecifica.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .build();
    }

    public CompetenciaEspecificaResponse toResponse(CompetenciaEspecifica competenciaEspecifica) {
        return CompetenciaEspecificaResponse.builder()
                .id(competenciaEspecifica.getId())
                .idPerfilEgreso(competenciaEspecifica.getPerfilEgreso().getId())
                .codigo(competenciaEspecifica.getCodigo())
                .nombre(competenciaEspecifica.getNombre())
                .descripcion(competenciaEspecifica.getDescripcion())
                .build();
    }
}
