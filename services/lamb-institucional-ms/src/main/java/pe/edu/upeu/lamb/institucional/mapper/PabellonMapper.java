package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.PabellonRequest;
import pe.edu.upeu.lamb.institucional.dto.PabellonResponse;
import pe.edu.upeu.lamb.institucional.entity.Pabellon;

@Component
public class PabellonMapper {

    public Pabellon toEntity(PabellonRequest request) {
        return Pabellon.builder()
                .nombre(request.getNombre())
                .idSede(request.getIdSede())
                .numeroPisos(request.getNumeroPisos())
                .descripcion(request.getDescripcion())
                .build();
    }

    public PabellonResponse toResponse(Pabellon entity) {
        return PabellonResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idSede(entity.getIdSede())
                .numeroPisos(entity.getNumeroPisos())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
