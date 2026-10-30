package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ActividadFormacionRequest;
import pe.edu.upeu.lamb.curriculo.dto.ActividadFormacionResponse;
import pe.edu.upeu.lamb.curriculo.entity.ActividadFormacion;
import org.springframework.stereotype.Component;

@Component
public class ActividadFormacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ActividadFormacion toEntity(ActividadFormacionRequest request) {
        return ActividadFormacion.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .tipo(request.getTipo())
                .creditos(request.getCreditos())
                .horas(request.getHoras())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ActividadFormacionResponse toResponse(ActividadFormacion actividadFormacion) {
        return ActividadFormacionResponse.builder()
                .id(actividadFormacion.getId())
                .codigo(actividadFormacion.getCodigo())
                .nombre(actividadFormacion.getNombre())
                .tipo(actividadFormacion.getTipo())
                .creditos(actividadFormacion.getCreditos())
                .horas(actividadFormacion.getHoras())
                .descripcion(actividadFormacion.getDescripcion())
                .build();
    }
}
