package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.UbigeoRequest;
import pe.edu.upeu.lamb.personas.dto.UbigeoResponse;
import pe.edu.upeu.lamb.personas.entity.Ubigeo;

@Component
public class UbigeoMapper {

    public Ubigeo toEntity(UbigeoRequest request) {
        return Ubigeo.builder()
                .pais(request.getPais())
                .departamento(request.getDepartamento())
                .provincia(request.getProvincia())
                .distrito(request.getDistrito())
                .build();
    }

    public UbigeoResponse toResponse(Ubigeo entity) {
        return UbigeoResponse.builder()
                .id(entity.getId())
                .pais(entity.getPais())
                .departamento(entity.getDepartamento())
                .provincia(entity.getProvincia())
                .distrito(entity.getDistrito())
                .build();
    }
}
