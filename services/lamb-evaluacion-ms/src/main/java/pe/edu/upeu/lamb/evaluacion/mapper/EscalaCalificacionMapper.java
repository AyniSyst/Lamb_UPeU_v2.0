package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.EscalaCalificacionRequest;
import pe.edu.upeu.lamb.evaluacion.dto.EscalaCalificacionResponse;
import pe.edu.upeu.lamb.evaluacion.entity.EscalaCalificacion;
import org.springframework.stereotype.Component;

@Component
public class EscalaCalificacionMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public EscalaCalificacion toEntity(EscalaCalificacionRequest request) {
        return EscalaCalificacion.builder()
                .codigo(request.getCodigo().trim())
                .nombre(request.getNombre().trim())
                .notaMinima(request.getNotaMinima())
                .notaMaxima(request.getNotaMaxima())
                .notaAprobatoria(request.getNotaAprobatoria())
                .decimales(request.getDecimales())
                .redondeo(request.getRedondeo())
                .vigente(request.getVigente())
                .build();
    }

    public EscalaCalificacionResponse toResponse(EscalaCalificacion escalaCalificacion) {
        return EscalaCalificacionResponse.builder()
                .id(escalaCalificacion.getId())
                .codigo(escalaCalificacion.getCodigo())
                .nombre(escalaCalificacion.getNombre())
                .notaMinima(escalaCalificacion.getNotaMinima())
                .notaMaxima(escalaCalificacion.getNotaMaxima())
                .notaAprobatoria(escalaCalificacion.getNotaAprobatoria())
                .decimales(escalaCalificacion.getDecimales())
                .redondeo(escalaCalificacion.getRedondeo())
                .vigente(escalaCalificacion.getVigente())
                .build();
    }
}
