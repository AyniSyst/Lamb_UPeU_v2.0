package pe.edu.upeu.lamb.evaluacion.mapper;

import pe.edu.upeu.lamb.evaluacion.dto.ActaComplementariaRequest;
import pe.edu.upeu.lamb.evaluacion.dto.ActaComplementariaResponse;
import pe.edu.upeu.lamb.evaluacion.entity.ActaComplementaria;
import org.springframework.stereotype.Component;

@Component
public class ActaComplementariaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public ActaComplementaria toEntity(ActaComplementariaRequest request) {
        return ActaComplementaria.builder()
                .idPersona(request.getIdPersona())
                .notaSustitutorio(request.getNotaSustitutorio())
                .fecha(request.getFecha())
                .observacion(request.getObservacion())
                .build();
    }

    public ActaComplementariaResponse toResponse(ActaComplementaria actaComplementaria) {
        return ActaComplementariaResponse.builder()
                .id(actaComplementaria.getId())
                .idActa(actaComplementaria.getActa().getId())
                .codigoCursoActa(actaComplementaria.getActa().getCodigoCurso())
                .idPersona(actaComplementaria.getIdPersona())
                .notaSustitutorio(actaComplementaria.getNotaSustitutorio())
                .fecha(actaComplementaria.getFecha())
                .observacion(actaComplementaria.getObservacion())
                .build();
    }
}
