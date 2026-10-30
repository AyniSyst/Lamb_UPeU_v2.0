package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.CalendarioFacultadEscuelaRequest;
import pe.edu.upeu.lamb.institucional.dto.CalendarioFacultadEscuelaResponse;
import pe.edu.upeu.lamb.institucional.entity.CalendarioFacultadEscuela;

@Component
public class CalendarioFacultadEscuelaMapper {

    public CalendarioFacultadEscuela toEntity(CalendarioFacultadEscuelaRequest request) {
        return CalendarioFacultadEscuela.builder()
                .idCalendarioAcademico(request.getIdCalendarioAcademico())
                .idFacultad(request.getIdFacultad())
                .idEscuela(request.getIdEscuela())
                .build();
    }

    public CalendarioFacultadEscuelaResponse toResponse(CalendarioFacultadEscuela entity) {
        return CalendarioFacultadEscuelaResponse.builder()
                .id(entity.getId())
                .idCalendarioAcademico(entity.getIdCalendarioAcademico())
                .idFacultad(entity.getIdFacultad())
                .idEscuela(entity.getIdEscuela())
                .build();
    }
}
