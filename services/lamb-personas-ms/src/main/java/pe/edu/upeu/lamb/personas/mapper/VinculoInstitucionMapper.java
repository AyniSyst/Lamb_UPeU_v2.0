package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.VinculoInstitucionRequest;
import pe.edu.upeu.lamb.personas.dto.VinculoInstitucionResponse;
import pe.edu.upeu.lamb.personas.entity.VinculoInstitucion;

@Component
public class VinculoInstitucionMapper {

    public VinculoInstitucion toEntity(VinculoInstitucionRequest request) {
        return VinculoInstitucion.builder()
                .idPersona(request.getIdPersona())
                .tipoVinculo(request.getTipoVinculo())
                .observacion(request.getObservacion())
                .build();
    }

    public VinculoInstitucionResponse toResponse(VinculoInstitucion entity) {
        return VinculoInstitucionResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .tipoVinculo(entity.getTipoVinculo())
                .observacion(entity.getObservacion())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
