package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.LimiteCreditosRequest;
import pe.edu.upeu.lamb.planificacion.dto.LimiteCreditosResponse;
import pe.edu.upeu.lamb.planificacion.entity.LimiteCreditos;
import org.springframework.stereotype.Component;

@Component
public class LimiteCreditosMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public LimiteCreditos toEntity(LimiteCreditosRequest request) {
        return LimiteCreditos.builder()
                .idPrograma(request.getIdPrograma())
                .ciclo(request.getCiclo())
                .creditosMinimos(request.getCreditosMinimos())
                .creditosMaximos(request.getCreditosMaximos())
                .build();
    }

    public LimiteCreditosResponse toResponse(LimiteCreditos limiteCreditos) {
        return LimiteCreditosResponse.builder()
                .id(limiteCreditos.getId())
                .idPeriodo(limiteCreditos.getPeriodo().getId())
                .codigoPeriodo(limiteCreditos.getPeriodo().getCodigo())
                .idPrograma(limiteCreditos.getIdPrograma())
                .ciclo(limiteCreditos.getCiclo())
                .creditosMinimos(limiteCreditos.getCreditosMinimos())
                .creditosMaximos(limiteCreditos.getCreditosMaximos())
                .build();
    }
}
