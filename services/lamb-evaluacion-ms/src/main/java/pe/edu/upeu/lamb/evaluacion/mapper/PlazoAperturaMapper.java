package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.PlazoAperturaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.PlazoAperturaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.PlazoApertura;
import org.springframework.stereotype.Component;

@Component
public class PlazoAperturaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public PlazoApertura toEntity(PlazoAperturaRequest request) {
        return PlazoApertura.builder()
                .idGrupo(request.getIdGrupo())
                .idDocente(request.getIdDocente())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .motivo(request.getMotivo())
                .build();
    }

    public PlazoAperturaResponse toResponse(PlazoApertura plazoApertura) {
        return PlazoAperturaResponse.builder()
                .id(plazoApertura.getId())
                .idGrupo(plazoApertura.getIdGrupo())
                .idDocente(plazoApertura.getIdDocente())
                .idSolicitud(plazoApertura.getSolicitud() != null ? plazoApertura.getSolicitud().getId() : null)
                .fechaInicio(plazoApertura.getFechaInicio())
                .fechaFin(plazoApertura.getFechaFin())
                .motivo(plazoApertura.getMotivo())
                .build();
    }
}
