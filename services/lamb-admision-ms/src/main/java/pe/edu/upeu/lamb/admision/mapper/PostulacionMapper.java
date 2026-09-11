package pe.edu.upeu.lamb.admision.mapper;

import pe.edu.upeu.lamb.admision.dto.PostulacionRequest;
import pe.edu.upeu.lamb.admision.dto.PostulacionResponse;
import pe.edu.upeu.lamb.admision.entity.EstadoPostulacion;
import pe.edu.upeu.lamb.admision.entity.Postulacion;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PostulacionMapper {

    public Postulacion toEntity(PostulacionRequest request) {
        return Postulacion.builder()
                .idPersona(request.getIdPersona())
                .idPrograma(request.getIdPrograma())
                .periodo(request.getPeriodo())
                .estado(EstadoPostulacion.INSCRITO)
                .fechaRegistro(Instant.now())
                .build();
    }

    public PostulacionResponse toResponse(Postulacion postulacion) {
        return PostulacionResponse.builder()
                .id(postulacion.getId())
                .idPersona(postulacion.getIdPersona())
                .idPrograma(postulacion.getIdPrograma())
                .periodo(postulacion.getPeriodo())
                .puntajeExamen(postulacion.getPuntajeExamen())
                .estado(postulacion.getEstado())
                .fechaRegistro(postulacion.getFechaRegistro())
                .build();
    }
}
