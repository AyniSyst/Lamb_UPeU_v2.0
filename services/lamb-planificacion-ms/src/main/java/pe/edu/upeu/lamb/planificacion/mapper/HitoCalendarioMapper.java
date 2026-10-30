package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.HitoCalendarioRequest;
import pe.edu.upeu.lamb.planificacion.dto.HitoCalendarioResponse;
import pe.edu.upeu.lamb.planificacion.entity.HitoCalendario;
import org.springframework.stereotype.Component;

@Component
public class HitoCalendarioMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public HitoCalendario toEntity(HitoCalendarioRequest request) {
        return HitoCalendario.builder()
                .tipo(request.getTipo())
                .nombre(request.getNombre().trim())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();
    }

    public HitoCalendarioResponse toResponse(HitoCalendario hitoCalendario) {
        return HitoCalendarioResponse.builder()
                .id(hitoCalendario.getId())
                .idCalendario(hitoCalendario.getCalendario().getId())
                .nombreCalendario(hitoCalendario.getCalendario().getNombre())
                .tipo(hitoCalendario.getTipo())
                .nombre(hitoCalendario.getNombre())
                .fechaInicio(hitoCalendario.getFechaInicio())
                .fechaFin(hitoCalendario.getFechaFin())
                .build();
    }
}
