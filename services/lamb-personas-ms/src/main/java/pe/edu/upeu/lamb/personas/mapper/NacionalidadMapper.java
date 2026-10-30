package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.NacionalidadRequest;
import pe.edu.upeu.lamb.personas.dto.NacionalidadResponse;
import pe.edu.upeu.lamb.personas.entity.Nacionalidad;

@Component
public class NacionalidadMapper {

    public Nacionalidad toEntity(NacionalidadRequest request) {
        return Nacionalidad.builder()
                .nombre(request.getNombre())
                .build();
    }

    public NacionalidadResponse toResponse(Nacionalidad entity) {
        return NacionalidadResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }
}
