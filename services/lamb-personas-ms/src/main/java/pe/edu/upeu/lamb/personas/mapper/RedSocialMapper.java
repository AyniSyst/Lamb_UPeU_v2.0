package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.RedSocialRequest;
import pe.edu.upeu.lamb.personas.dto.RedSocialResponse;
import pe.edu.upeu.lamb.personas.entity.RedSocial;

@Component
public class RedSocialMapper {

    public RedSocial toEntity(RedSocialRequest request) {
        return RedSocial.builder()
                .idPersona(request.getIdPersona())
                .red(request.getRed())
                .url(request.getUrl())
                .build();
    }

    public RedSocialResponse toResponse(RedSocial entity) {
        return RedSocialResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .red(entity.getRed())
                .url(entity.getUrl())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
