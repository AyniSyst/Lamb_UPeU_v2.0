package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.SeguimientoResponse;
import pe.edu.upeu.lamb.admision.entity.SeguimientoPostulante;

@Component
public class SeguimientoMapper {
    public SeguimientoResponse toResponse(SeguimientoPostulante s) {
        return SeguimientoResponse.builder()
                .id(s.getId())
                .idPostulante(s.getPostulante().getId())
                .codigoPostulante(s.getPostulante().getCodigoPostulante())
                .canal(s.getCanal().name())
                .medioCaptacion(s.getMedioCaptacion())
                .asunto(s.getAsunto())
                .detalle(s.getDetalle())
                .resultado(s.getResultado().name())
                .idResponsable(s.getIdResponsable())
                .fechaContacto(s.getFechaContacto())
                .proximoContacto(s.getProximoContacto())
                .build();
    }
}
