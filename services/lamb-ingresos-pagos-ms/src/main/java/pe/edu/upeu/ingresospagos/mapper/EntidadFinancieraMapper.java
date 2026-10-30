package pe.edu.upeu.ingresospagos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraRequest;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraResponse;
import pe.edu.upeu.ingresospagos.entity.EntidadFinanciera;

@Mapper(componentModel = "spring")
public interface EntidadFinancieraMapper {

    EntidadFinancieraResponse toResponse(EntidadFinanciera entidadFinanciera);

    @Mapping(target = "id", ignore = true)
    EntidadFinanciera toEntity(EntidadFinancieraRequest request);
}
