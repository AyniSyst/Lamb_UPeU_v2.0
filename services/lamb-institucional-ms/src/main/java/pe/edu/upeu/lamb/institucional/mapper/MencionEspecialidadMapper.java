package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.MencionEspecialidadRequest;
import pe.edu.upeu.lamb.institucional.dto.MencionEspecialidadResponse;
import pe.edu.upeu.lamb.institucional.entity.MencionEspecialidad;

@Component
public class MencionEspecialidadMapper {

    public MencionEspecialidad toEntity(MencionEspecialidadRequest request) {
        return MencionEspecialidad.builder()
                .nombre(request.getNombre())
                .idPrograma(request.getIdPrograma())
                .descripcion(request.getDescripcion())
                .build();
    }

    public MencionEspecialidadResponse toResponse(MencionEspecialidad entity) {
        return MencionEspecialidadResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idPrograma(entity.getIdPrograma())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
