package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.AmbienteRequest;
import pe.edu.upeu.lamb.institucional.dto.AmbienteResponse;
import pe.edu.upeu.lamb.institucional.entity.Ambiente;

@Component
public class AmbienteMapper {

    public Ambiente toEntity(AmbienteRequest request) {
        return Ambiente.builder()
                .nombre(request.getNombre())
                .idPabellon(request.getIdPabellon())
                .idSede(request.getIdSede())
                .tipoAmbiente(request.getTipoAmbiente())
                .capacidad(request.getCapacidad())
                .build();
    }

    public AmbienteResponse toResponse(Ambiente entity) {
        return AmbienteResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idPabellon(entity.getIdPabellon())
                .idSede(entity.getIdSede())
                .tipoAmbiente(entity.getTipoAmbiente())
                .capacidad(entity.getCapacidad())
                .build();
    }
}
