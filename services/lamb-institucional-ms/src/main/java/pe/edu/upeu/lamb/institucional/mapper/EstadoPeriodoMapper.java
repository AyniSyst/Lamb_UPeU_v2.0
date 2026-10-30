package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.EstadoPeriodoRequest;
import pe.edu.upeu.lamb.institucional.dto.EstadoPeriodoResponse;
import pe.edu.upeu.lamb.institucional.entity.EstadoPeriodo;

@Component
public class EstadoPeriodoMapper {

    public EstadoPeriodo toEntity(EstadoPeriodoRequest request) {
        return EstadoPeriodo.builder()
                .idPeriodoAcademico(request.getIdPeriodoAcademico())
                .estado(request.getEstado())
                .fechaCambio(request.getFechaCambio())
                .build();
    }

    public EstadoPeriodoResponse toResponse(EstadoPeriodo entity) {
        return EstadoPeriodoResponse.builder()
                .id(entity.getId())
                .idPeriodoAcademico(entity.getIdPeriodoAcademico())
                .estado(entity.getEstado())
                .fechaCambio(entity.getFechaCambio())
                .build();
    }
}
