package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ResolucionLicenciamientoRequest;
import pe.edu.upeu.lamb.institucional.dto.ResolucionLicenciamientoResponse;
import pe.edu.upeu.lamb.institucional.entity.ResolucionLicenciamiento;

@Component
public class ResolucionLicenciamientoMapper {

    public ResolucionLicenciamiento toEntity(ResolucionLicenciamientoRequest request) {
        return ResolucionLicenciamiento.builder()
                .numeroResolucion(request.getNumeroResolucion())
                .idInstitucion(request.getIdInstitucion())
                .tipoLicenciamiento(request.getTipoLicenciamiento())
                .fechaEmision(request.getFechaEmision())
                .fechaVigencia(request.getFechaVigencia())
                .entidadEmisora(request.getEntidadEmisora())
                .observaciones(request.getObservaciones())
                .build();
    }

    public ResolucionLicenciamientoResponse toResponse(ResolucionLicenciamiento entity) {
        return ResolucionLicenciamientoResponse.builder()
                .id(entity.getId())
                .numeroResolucion(entity.getNumeroResolucion())
                .idInstitucion(entity.getIdInstitucion())
                .tipoLicenciamiento(entity.getTipoLicenciamiento())
                .fechaEmision(entity.getFechaEmision())
                .fechaVigencia(entity.getFechaVigencia())
                .entidadEmisora(entity.getEntidadEmisora())
                .observaciones(entity.getObservaciones())
                .build();
    }
}
