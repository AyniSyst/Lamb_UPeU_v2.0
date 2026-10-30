package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.ConvenioInstitucional;

@Component
public class ConvenioInstitucionalMapper {

    public ConvenioInstitucional toEntity(ConvenioInstitucionalRequest request) {
        return ConvenioInstitucional.builder()
                .idInstitucion(request.getIdInstitucion())
                .nombreConvenio(request.getNombreConvenio())
                .institucionSocia(request.getInstitucionSocia())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .descripcion(request.getDescripcion())
                .build();
    }

    public ConvenioInstitucionalResponse toResponse(ConvenioInstitucional entity) {
        return ConvenioInstitucionalResponse.builder()
                .id(entity.getId())
                .idInstitucion(entity.getIdInstitucion())
                .nombreConvenio(entity.getNombreConvenio())
                .institucionSocia(entity.getInstitucionSocia())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
