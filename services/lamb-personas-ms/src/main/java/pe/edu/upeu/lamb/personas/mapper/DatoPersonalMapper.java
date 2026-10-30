package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.DatoPersonalRequest;
import pe.edu.upeu.lamb.personas.dto.DatoPersonalResponse;
import pe.edu.upeu.lamb.personas.entity.DatoPersonal;

@Component
public class DatoPersonalMapper {

    public DatoPersonal toEntity(DatoPersonalRequest request) {
        return DatoPersonal.builder()
                .idPersona(request.getIdPersona())
                .fechaNacimiento(request.getFechaNacimiento())
                .lugarNacimiento(request.getLugarNacimiento())
                .idSexo(request.getIdSexo())
                .idEstadoCivil(request.getIdEstadoCivil())
                .idNacionalidad(request.getIdNacionalidad())
                .idUbigeoNacimiento(request.getIdUbigeoNacimiento())
                .build();
    }

    public DatoPersonalResponse toResponse(DatoPersonal entity) {
        return DatoPersonalResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .fechaNacimiento(entity.getFechaNacimiento())
                .lugarNacimiento(entity.getLugarNacimiento())
                .idSexo(entity.getIdSexo())
                .idEstadoCivil(entity.getIdEstadoCivil())
                .idNacionalidad(entity.getIdNacionalidad())
                .idUbigeoNacimiento(entity.getIdUbigeoNacimiento())
                .build();
    }
}
