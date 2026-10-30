package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.PabellonRequest;
import pe.edu.upeu.lamb.planificacion.dto.PabellonResponse;
import pe.edu.upeu.lamb.planificacion.entity.Pabellon;
import org.springframework.stereotype.Component;

@Component
public class PabellonMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public Pabellon toEntity(PabellonRequest request) {
        return Pabellon.builder()
                .idSede(request.getIdSede())
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .numeroPisos(request.getNumeroPisos())
                .build();
    }

    public PabellonResponse toResponse(Pabellon pabellon) {
        return PabellonResponse.builder()
                .id(pabellon.getId())
                .idSede(pabellon.getIdSede())
                .codigo(pabellon.getCodigo())
                .nombre(pabellon.getNombre())
                .numeroPisos(pabellon.getNumeroPisos())
                .build();
    }
}
