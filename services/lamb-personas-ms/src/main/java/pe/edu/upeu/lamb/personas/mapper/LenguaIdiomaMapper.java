package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.LenguaIdiomaRequest;
import pe.edu.upeu.lamb.personas.dto.LenguaIdiomaResponse;
import pe.edu.upeu.lamb.personas.entity.LenguaIdioma;

@Component
public class LenguaIdiomaMapper {

    public LenguaIdioma toEntity(LenguaIdiomaRequest request) {
        return LenguaIdioma.builder()
                .idPersona(request.getIdPersona())
                .idioma(request.getIdioma())
                .nivel(request.getNivel())
                .build();
    }

    public LenguaIdiomaResponse toResponse(LenguaIdioma entity) {
        return LenguaIdiomaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idioma(entity.getIdioma())
                .nivel(entity.getNivel())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
