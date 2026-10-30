package pe.edu.upeu.lamb.planificacion.mapper;

import pe.edu.upeu.lamb.planificacion.dto.SesionClaseRequest;
import pe.edu.upeu.lamb.planificacion.dto.SesionClaseResponse;
import pe.edu.upeu.lamb.planificacion.entity.SesionClase;
import org.springframework.stereotype.Component;

@Component
public class SesionClaseMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public SesionClase toEntity(SesionClaseRequest request) {
        return SesionClase.builder()
                .numero(request.getNumero())
                .semana(request.getSemana())
                .tema(request.getTema().trim())
                .idUnidadAprendizaje(request.getIdUnidadAprendizaje())
                .actividades(request.getActividades())
                .build();
    }

    public SesionClaseResponse toResponse(SesionClase sesionClase) {
        return SesionClaseResponse.builder()
                .id(sesionClase.getId())
                .idSilabo(sesionClase.getSilabo().getId())
                .numero(sesionClase.getNumero())
                .semana(sesionClase.getSemana())
                .tema(sesionClase.getTema())
                .idUnidadAprendizaje(sesionClase.getIdUnidadAprendizaje())
                .actividades(sesionClase.getActividades())
                .build();
    }
}
