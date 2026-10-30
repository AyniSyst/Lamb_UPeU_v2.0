package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.PeriodoProgramaSedeRequest;
import pe.edu.upeu.lamb.institucional.dto.PeriodoProgramaSedeResponse;
import pe.edu.upeu.lamb.institucional.entity.PeriodoProgramaSede;

@Component
public class PeriodoProgramaSedeMapper {

    public PeriodoProgramaSede toEntity(PeriodoProgramaSedeRequest request) {
        return PeriodoProgramaSede.builder()
                .idPeriodoAcademico(request.getIdPeriodoAcademico())
                .idPrograma(request.getIdPrograma())
                .idSede(request.getIdSede())
                .vigente(request.getVigente())
                .build();
    }

    public PeriodoProgramaSedeResponse toResponse(PeriodoProgramaSede entity) {
        return PeriodoProgramaSedeResponse.builder()
                .id(entity.getId())
                .idPeriodoAcademico(entity.getIdPeriodoAcademico())
                .idPrograma(entity.getIdPrograma())
                .idSede(entity.getIdSede())
                .vigente(entity.getVigente())
                .build();
    }
}
