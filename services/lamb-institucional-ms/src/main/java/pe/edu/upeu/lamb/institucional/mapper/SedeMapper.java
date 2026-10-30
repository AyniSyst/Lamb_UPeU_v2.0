package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.SedeRequest;
import pe.edu.upeu.lamb.institucional.dto.SedeResponse;
import pe.edu.upeu.lamb.institucional.entity.Sede;

@Component
public class SedeMapper {

    public Sede toEntity(SedeRequest request) {
        return Sede.builder()
                .nombre(request.getNombre())
                .idCampus(request.getIdCampus())
                .idTipoSede(request.getIdTipoSede())
                .direccion(request.getDireccion())
                .build();
    }

    public SedeResponse toResponse(Sede entity) {
        return SedeResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idCampus(entity.getIdCampus())
                .idTipoSede(entity.getIdTipoSede())
                .direccion(entity.getDireccion())
                .build();
    }
}
