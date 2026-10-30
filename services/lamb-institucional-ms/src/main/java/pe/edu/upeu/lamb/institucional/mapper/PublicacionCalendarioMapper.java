package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.PublicacionCalendarioRequest;
import pe.edu.upeu.lamb.institucional.dto.PublicacionCalendarioResponse;
import pe.edu.upeu.lamb.institucional.entity.PublicacionCalendario;

@Component
public class PublicacionCalendarioMapper {

    public PublicacionCalendario toEntity(PublicacionCalendarioRequest request) {
        return PublicacionCalendario.builder()
                .idCalendarioAcademico(request.getIdCalendarioAcademico())
                .fechaPublicacion(request.getFechaPublicacion())
                .urlDocumento(request.getUrlDocumento())
                .build();
    }

    public PublicacionCalendarioResponse toResponse(PublicacionCalendario entity) {
        return PublicacionCalendarioResponse.builder()
                .id(entity.getId())
                .idCalendarioAcademico(entity.getIdCalendarioAcademico())
                .fechaPublicacion(entity.getFechaPublicacion())
                .urlDocumento(entity.getUrlDocumento())
                .build();
    }
}
