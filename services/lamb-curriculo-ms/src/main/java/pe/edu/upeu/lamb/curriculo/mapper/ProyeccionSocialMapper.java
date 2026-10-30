package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.ProyeccionSocialRequest;
import pe.edu.upeu.lamb.curriculo.dto.ProyeccionSocialResponse;
import pe.edu.upeu.lamb.curriculo.entity.ProyeccionSocial;
import org.springframework.stereotype.Component;

@Component
public class ProyeccionSocialMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ProyeccionSocial toEntity(ProyeccionSocialRequest request) {
        return ProyeccionSocial.builder()
                .idPrograma(request.getIdPrograma())
                .nombre(request.getNombre().trim())
                .descripcion(request.getDescripcion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .horas(request.getHoras())
                .estado(request.getEstado())
                .build();
    }

    public ProyeccionSocialResponse toResponse(ProyeccionSocial proyeccionSocial) {
        return ProyeccionSocialResponse.builder()
                .id(proyeccionSocial.getId())
                .idPrograma(proyeccionSocial.getIdPrograma())
                .nombre(proyeccionSocial.getNombre())
                .descripcion(proyeccionSocial.getDescripcion())
                .fechaInicio(proyeccionSocial.getFechaInicio())
                .fechaFin(proyeccionSocial.getFechaFin())
                .horas(proyeccionSocial.getHoras())
                .estado(proyeccionSocial.getEstado())
                .build();
    }
}
