package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.GradoTituloRequest;
import pe.edu.upeu.lamb.institucional.dto.GradoTituloResponse;
import pe.edu.upeu.lamb.institucional.entity.GradoTitulo;

@Component
public class GradoTituloMapper {

    public GradoTitulo toEntity(GradoTituloRequest request) {
        return GradoTitulo.builder()
                .idPrograma(request.getIdPrograma())
                .grado(request.getGrado())
                .titulo(request.getTitulo())
                .build();
    }

    public GradoTituloResponse toResponse(GradoTitulo entity) {
        return GradoTituloResponse.builder()
                .id(entity.getId())
                .idPrograma(entity.getIdPrograma())
                .grado(entity.getGrado())
                .titulo(entity.getTitulo())
                .build();
    }
}
