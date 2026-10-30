package pe.edu.upeu.lamb.admision.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.admision.dto.AdjudicacionResponse;
import pe.edu.upeu.lamb.admision.entity.AdjudicacionVacante;

@Component
public class AdjudicacionMapper {
    public AdjudicacionResponse toResponse(AdjudicacionVacante a) {
        return AdjudicacionResponse.builder()
                .id(a.getId())
                .idPostulante(a.getPostulante().getId())
                .codigoPostulante(a.getPostulante().getCodigoPostulante())
                .idModalidad(a.getModalidad().getId())
                .codigoModalidad(a.getModalidad().getCodigo())
                .idPrograma(a.getIdPrograma())
                .puntajeFinal(a.getPuntajeFinal())
                .ordenMerito(a.getOrdenMerito())
                .estado(a.getEstado().name())
                .numeroResolucion(a.getNumeroResolucion())
                .fechaAdjudicacion(a.getFechaAdjudicacion())
                .fechaPublicacion(a.getFechaPublicacion())
                .observacion(a.getObservacion())
                .version(a.getVersion())
                .build();
    }
}
