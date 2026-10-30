package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.ingresospagos.entity.ConvenioInstitucional;

@Mapper(componentModel = "spring")
public interface ConvenioInstitucionalMapper {

    ConvenioInstitucionalResponse toResponse(ConvenioInstitucional convenioInstitucional);

    @Mapping(target = "id", ignore = true)
    ConvenioInstitucional toEntity(ConvenioInstitucionalRequest request);
}
