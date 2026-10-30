package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.EquipamientoAmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.EquipamientoAmbienteResponse;
import pe.edu.upeu.lamb.planificacion.entity.EquipamientoAmbiente;
import org.springframework.stereotype.Component;

@Component
public class EquipamientoAmbienteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EquipamientoAmbiente toEntity(EquipamientoAmbienteRequest request) {
        return EquipamientoAmbiente.builder()
                .nombre(request.getNombre().trim())
                .cantidad(request.getCantidad())
                .codigoPatrimonial(request.getCodigoPatrimonial())
                .estado(request.getEstado())
                .build();
    }

    public EquipamientoAmbienteResponse toResponse(EquipamientoAmbiente equipamientoAmbiente) {
        return EquipamientoAmbienteResponse.builder()
                .id(equipamientoAmbiente.getId())
                .idAmbiente(equipamientoAmbiente.getAmbiente().getId())
                .codigoAmbiente(equipamientoAmbiente.getAmbiente().getCodigo())
                .nombre(equipamientoAmbiente.getNombre())
                .cantidad(equipamientoAmbiente.getCantidad())
                .codigoPatrimonial(equipamientoAmbiente.getCodigoPatrimonial())
                .estado(equipamientoAmbiente.getEstado())
                .build();
    }
}
