package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ParametroSedeEscuelaRequest;
import pe.edu.upeu.lamb.institucional.dto.ParametroSedeEscuelaResponse;
import pe.edu.upeu.lamb.institucional.entity.ParametroSedeEscuela;

@Component
public class ParametroSedeEscuelaMapper {

    public ParametroSedeEscuela toEntity(ParametroSedeEscuelaRequest request) {
        return ParametroSedeEscuela.builder()
                .clave(request.getClave())
                .valor(request.getValor())
                .idSede(request.getIdSede())
                .idEscuela(request.getIdEscuela())
                .build();
    }

    public ParametroSedeEscuelaResponse toResponse(ParametroSedeEscuela entity) {
        return ParametroSedeEscuelaResponse.builder()
                .id(entity.getId())
                .clave(entity.getClave())
                .valor(entity.getValor())
                .idSede(entity.getIdSede())
                .idEscuela(entity.getIdEscuela())
                .build();
    }
}
