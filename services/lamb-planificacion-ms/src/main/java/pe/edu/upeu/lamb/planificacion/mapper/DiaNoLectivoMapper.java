package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.DiaNoLectivoRequest;
import pe.edu.upeu.lamb.planificacion.dto.DiaNoLectivoResponse;
import pe.edu.upeu.lamb.planificacion.entity.DiaNoLectivo;
import org.springframework.stereotype.Component;

@Component
public class DiaNoLectivoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public DiaNoLectivo toEntity(DiaNoLectivoRequest request) {
        return DiaNoLectivo.builder()
                .fecha(request.getFecha())
                .motivo(request.getMotivo().trim())
                .tipo(request.getTipo())
                .build();
    }

    public DiaNoLectivoResponse toResponse(DiaNoLectivo diaNoLectivo) {
        return DiaNoLectivoResponse.builder()
                .id(diaNoLectivo.getId())
                .idCalendario(diaNoLectivo.getCalendario().getId())
                .nombreCalendario(diaNoLectivo.getCalendario().getNombre())
                .fecha(diaNoLectivo.getFecha())
                .motivo(diaNoLectivo.getMotivo())
                .tipo(diaNoLectivo.getTipo())
                .build();
    }
}
