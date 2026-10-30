package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ProgramaSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.ProgramaSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.ProgramaSede;

@Component
public class ProgramaSedeMapper {

    public ProgramaSede toEntity(ProgramaSedeRequest request) {
        return ProgramaSede.builder()
                .idPrograma(request.getIdPrograma())
                .idSede(request.getIdSede())
                .vigente(request.getVigente())
                .build();
    }

    public ProgramaSedeResponse toResponse(ProgramaSede entity) {
        return ProgramaSedeResponse.builder()
                .id(entity.getId())
                .idPrograma(entity.getIdPrograma())
                .idSede(entity.getIdSede())
                .vigente(entity.getVigente())
                .build();
    }
}
