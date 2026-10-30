package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.HistorialDocumentoIdentidadRequest;
import pe.edu.upeu.lamb.personas.dto.HistorialDocumentoIdentidadResponse;
import pe.edu.upeu.lamb.personas.entity.HistorialDocumentoIdentidad;

@Component
public class HistorialDocumentoIdentidadMapper {

    public HistorialDocumentoIdentidad toEntity(HistorialDocumentoIdentidadRequest request) {
        return HistorialDocumentoIdentidad.builder()
                .idPersona(request.getIdPersona())
                .tipoCambio(request.getTipoCambio())
                .numeroAnterior(request.getNumeroAnterior())
                .numeroNuevo(request.getNumeroNuevo())
                .build();
    }

    public HistorialDocumentoIdentidadResponse toResponse(HistorialDocumentoIdentidad entity) {
        return HistorialDocumentoIdentidadResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .tipoCambio(entity.getTipoCambio())
                .numeroAnterior(entity.getNumeroAnterior())
                .numeroNuevo(entity.getNumeroNuevo())
                .fechaCambio(entity.getFechaCambio())
                .build();
    }
}
