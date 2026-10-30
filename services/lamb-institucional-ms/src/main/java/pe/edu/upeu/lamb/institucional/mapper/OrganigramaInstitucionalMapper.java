package pe.edu.upeu.lamb.institucional.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.institucional.dto.OrganigramaInstitucionalRequest;
import pe.edu.upeu.lamb.institucional.dto.OrganigramaInstitucionalResponse;
import pe.edu.upeu.lamb.institucional.entity.OrganigramaInstitucional;

@Component
public class OrganigramaInstitucionalMapper {

    public OrganigramaInstitucional toEntity(OrganigramaInstitucionalRequest request) {
        return OrganigramaInstitucional.builder()
                .nombre(request.getNombre())
                .idInstitucion(request.getIdInstitucion())
                .urlDocumento(request.getUrlDocumento())
                .vigente(request.getVigente())
                .build();
    }

    public OrganigramaInstitucionalResponse toResponse(OrganigramaInstitucional entity) {
        return OrganigramaInstitucionalResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .idInstitucion(entity.getIdInstitucion())
                .urlDocumento(entity.getUrlDocumento())
                .vigente(entity.getVigente())
                .build();
    }
}
