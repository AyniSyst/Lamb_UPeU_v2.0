package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoIdiomaRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoIdiomaResponse;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoIdioma;
import org.springframework.stereotype.Component;

@Component
public class RequisitoIdiomaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RequisitoIdioma toEntity(RequisitoIdiomaRequest request) {
        return RequisitoIdioma.builder()
                .idioma(request.getIdioma().trim())
                .tipo(request.getTipo())
                .nivel(request.getNivel().trim())
                .modoAcreditacion(request.getModoAcreditacion())
                .build();
    }

    public RequisitoIdiomaResponse toResponse(RequisitoIdioma requisitoIdioma) {
        return RequisitoIdiomaResponse.builder()
                .id(requisitoIdioma.getId())
                .idPlan(requisitoIdioma.getPlan().getId())
                .versionPlan(requisitoIdioma.getPlan().getVersion())
                .idioma(requisitoIdioma.getIdioma())
                .tipo(requisitoIdioma.getTipo())
                .nivel(requisitoIdioma.getNivel())
                .modoAcreditacion(requisitoIdioma.getModoAcreditacion())
                .build();
    }
}
