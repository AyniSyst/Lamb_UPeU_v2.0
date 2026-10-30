package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaResponse;
import pe.edu.upeu.ingresospagos.entity.BecaAsignada;

@Mapper(componentModel = "spring", uses = BecaMapper.class)
public interface BecaAsignadaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "beca", ignore = true)
    @Mapping(target = "fechaAsignacion", ignore = true)
    BecaAsignada toEntity(BecaAsignadaRequest request);

    BecaAsignadaResponse toResponse(BecaAsignada becaAsignada);
}
