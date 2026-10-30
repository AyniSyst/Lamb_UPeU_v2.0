package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.DireccionRequest;
import pe.edu.upeu.lamb.personas.dto.DireccionResponse;
import pe.edu.upeu.lamb.personas.entity.Direccion;

@Component
public class DireccionMapper {

    public Direccion toEntity(DireccionRequest request) {
        return Direccion.builder()
                .idPersona(request.getIdPersona())
                .idTipoDireccion(request.getIdTipoDireccion())
                .idUbigeo(request.getIdUbigeo())
                .direccion(request.getDireccion())
                .build();
    }

    public DireccionResponse toResponse(Direccion entity) {
        return DireccionResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .idTipoDireccion(entity.getIdTipoDireccion())
                .idUbigeo(entity.getIdUbigeo())
                .direccion(entity.getDireccion())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
