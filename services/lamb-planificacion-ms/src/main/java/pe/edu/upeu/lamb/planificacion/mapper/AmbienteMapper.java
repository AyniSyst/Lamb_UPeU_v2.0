package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.AmbienteRequest;
import pe.edu.upeu.lamb.planificacion.dto.AmbienteResponse;
import pe.edu.upeu.lamb.planificacion.entity.Ambiente;
import org.springframework.stereotype.Component;

@Component
public class AmbienteMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public Ambiente toEntity(AmbienteRequest request) {
        return Ambiente.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .piso(request.getPiso())
                .aforo(request.getAforo())
                .activo(request.getActivo())
                .build();
    }

    public AmbienteResponse toResponse(Ambiente ambiente) {
        return AmbienteResponse.builder()
                .id(ambiente.getId())
                .idPabellon(ambiente.getPabellon().getId())
                .codigoPabellon(ambiente.getPabellon().getCodigo())
                .idTipoAmbiente(ambiente.getTipoAmbiente().getId())
                .codigoTipoAmbiente(ambiente.getTipoAmbiente().getCodigo())
                .codigo(ambiente.getCodigo())
                .nombre(ambiente.getNombre())
                .piso(ambiente.getPiso())
                .aforo(ambiente.getAforo())
                .activo(ambiente.getActivo())
                .build();
    }
}
