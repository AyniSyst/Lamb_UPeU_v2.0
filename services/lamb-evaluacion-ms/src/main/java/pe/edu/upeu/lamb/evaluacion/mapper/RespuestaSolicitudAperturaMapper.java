package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudAperturaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.RespuestaSolicitudAperturaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.RespuestaSolicitudApertura;
import org.springframework.stereotype.Component;

@Component
public class RespuestaSolicitudAperturaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RespuestaSolicitudApertura toEntity(RespuestaSolicitudAperturaRequest request) {
        return RespuestaSolicitudApertura.builder()
                .aprobada(request.getAprobada())
                .observacion(request.getObservacion())
                .build();
    }

    public RespuestaSolicitudAperturaResponse toResponse(RespuestaSolicitudApertura respuestaSolicitudApertura) {
        return RespuestaSolicitudAperturaResponse.builder()
                .id(respuestaSolicitudApertura.getId())
                .idSolicitud(respuestaSolicitudApertura.getSolicitud().getId())
                .aprobada(respuestaSolicitudApertura.getAprobada())
                .observacion(respuestaSolicitudApertura.getObservacion())
                .idResponsable(respuestaSolicitudApertura.getIdResponsable())
                .fecha(respuestaSolicitudApertura.getFecha())
                .build();
    }
}
