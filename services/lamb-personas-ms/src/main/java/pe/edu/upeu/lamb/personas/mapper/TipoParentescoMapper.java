package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.TipoParentescoRequest;
import pe.edu.upeu.lamb.personas.dto.TipoParentescoResponse;
import pe.edu.upeu.lamb.personas.entity.TipoParentesco;

@Component
public class TipoParentescoMapper {

    public TipoParentesco toEntity(TipoParentescoRequest request) {
        return TipoParentesco.builder()
                .nombre(request.getNombre())
                .build();
    }

    public TipoParentescoResponse toResponse(TipoParentesco entity) {
        return TipoParentescoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
