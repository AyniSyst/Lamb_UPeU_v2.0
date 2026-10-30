package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.VoluntariadoRequest;
import pe.edu.upeu.lamb.curriculo.dto.VoluntariadoResponse;
import pe.edu.upeu.lamb.curriculo.entity.Voluntariado;
import org.springframework.stereotype.Component;

@Component
public class VoluntariadoMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public Voluntariado toEntity(VoluntariadoRequest request) {
        return Voluntariado.builder()
                .nombre(request.getNombre().trim())
                .organizacion(request.getOrganizacion().trim())
                .tipo(request.getTipo())
                .horasRequeridas(request.getHorasRequeridas())
                .descripcion(request.getDescripcion())
                .activo(request.getActivo())
                .build();
    }

    public VoluntariadoResponse toResponse(Voluntariado voluntariado) {
        return VoluntariadoResponse.builder()
                .id(voluntariado.getId())
                .nombre(voluntariado.getNombre())
                .organizacion(voluntariado.getOrganizacion())
                .tipo(voluntariado.getTipo())
                .horasRequeridas(voluntariado.getHorasRequeridas())
                .descripcion(voluntariado.getDescripcion())
                .activo(voluntariado.getActivo())
                .build();
    }
}
