package pe.edu.upeu.lamb.institucional.mapper;

import pe.edu.upeu.lamb.institucional.dto.FacultadRequest;
import pe.edu.upeu.lamb.institucional.dto.FacultadResponse;
import pe.edu.upeu.lamb.institucional.entity.Facultad;
import org.springframework.stereotype.Component;

@Component
public class FacultadMapper {

    public Facultad toEntity(FacultadRequest request) {
        return Facultad.builder()
                .nombre(request.getNombre())
                .decano(request.getDecano())
                .build();
    }

    public FacultadResponse toResponse(Facultad facultad) {
        return FacultadResponse.builder()
                .id(facultad.getId())
                .nombre(facultad.getNombre())
                .decano(facultad.getDecano())
                .build();
    }
}
