package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.CambioNombreApellidoRequest;
import pe.edu.upeu.lamb.personas.dto.CambioNombreApellidoResponse;
import pe.edu.upeu.lamb.personas.entity.CambioNombreApellido;

@Component
public class CambioNombreApellidoMapper {

    public CambioNombreApellido toEntity(CambioNombreApellidoRequest request) {
        return CambioNombreApellido.builder()
                .idPersona(request.getIdPersona())
                .nombreAnterior(request.getNombreAnterior())
                .nombreNuevo(request.getNombreNuevo())
                .sustento(request.getSustento())
                .build();
    }

    public CambioNombreApellidoResponse toResponse(CambioNombreApellido entity) {
        return CambioNombreApellidoResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .nombreAnterior(entity.getNombreAnterior())
                .nombreNuevo(entity.getNombreNuevo())
                .sustento(entity.getSustento())
                .fechaCambio(entity.getFechaCambio())
                .build();
    }
}
