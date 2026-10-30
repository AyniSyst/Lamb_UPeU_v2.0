package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.PlantillaCertificadoRequest;
import pe.edu.upeu.lms.dto.PlantillaCertificadoResponse;
import pe.edu.upeu.lms.entity.PlantillaCertificado;

@Mapper(componentModel = "spring")
public interface PlantillaCertificadoMapper {

    PlantillaCertificadoResponse toResponse(PlantillaCertificado plantillaCertificado);

    @Mapping(target = "id", ignore = true)
    PlantillaCertificado toEntity(PlantillaCertificadoRequest request);
}
