package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.AmpliacionCierrePeriodoRequest;
import pe.edu.upeu.lamb.institucional.dto.AmpliacionCierrePeriodoResponse;
import pe.edu.upeu.lamb.institucional.entity.AmpliacionCierrePeriodo;

@Component
public class AmpliacionCierrePeriodoMapper {

    public AmpliacionCierrePeriodo toEntity(AmpliacionCierrePeriodoRequest request) {
        return AmpliacionCierrePeriodo.builder()
                .idPeriodoAcademico(request.getIdPeriodoAcademico())
                .tipoAccion(request.getTipoAccion())
                .numeroResolucion(request.getNumeroResolucion())
                .fechaAccion(request.getFechaAccion())
                .descripcion(request.getDescripcion())
                .build();
    }

    public AmpliacionCierrePeriodoResponse toResponse(AmpliacionCierrePeriodo entity) {
        return AmpliacionCierrePeriodoResponse.builder()
                .id(entity.getId())
                .idPeriodoAcademico(entity.getIdPeriodoAcademico())
                .tipoAccion(entity.getTipoAccion())
                .numeroResolucion(entity.getNumeroResolucion())
                .fechaAccion(entity.getFechaAccion())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
