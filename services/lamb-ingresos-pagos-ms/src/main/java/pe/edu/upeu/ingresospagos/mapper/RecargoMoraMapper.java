package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraRequest;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraResponse;
import pe.edu.upeu.ingresospagos.entity.RecargoMora;

@Mapper(componentModel = "spring")
public interface RecargoMoraMapper {

    RecargoMoraResponse toResponse(RecargoMora recargoMora);

    @Mapping(target = "id", ignore = true)
    RecargoMora toEntity(RecargoMoraRequest request);
}
