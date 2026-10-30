package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ConsentimientoDatosRequest;
import pe.edu.upeu.lamb.personas.dto.ConsentimientoDatosResponse;
import pe.edu.upeu.lamb.personas.entity.ConsentimientoDatos;

@Component
public class ConsentimientoDatosMapper {

    public ConsentimientoDatos toEntity(ConsentimientoDatosRequest request) {
        return ConsentimientoDatos.builder()
                .idPersona(request.getIdPersona())
                .tipoConsentimiento(request.getTipoConsentimiento())
                .aceptado(request.getAceptado())
                .build();
    }

    public ConsentimientoDatosResponse toResponse(ConsentimientoDatos entity) {
        return ConsentimientoDatosResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .tipoConsentimiento(entity.getTipoConsentimiento())
                .aceptado(entity.getAceptado())
                .fechaConsentimiento(entity.getFechaConsentimiento())
                .build();
    }
}
