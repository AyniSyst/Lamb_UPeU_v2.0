package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.SexoRequest;
import pe.edu.upeu.lamb.personas.dto.SexoResponse;
import pe.edu.upeu.lamb.personas.entity.Sexo;

@Component
public class SexoMapper {

    public Sexo toEntity(SexoRequest request) {
        return Sexo.builder()
                .nombre(request.getNombre())
                .build();
    }

    public SexoResponse toResponse(Sexo entity) {
        return SexoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
