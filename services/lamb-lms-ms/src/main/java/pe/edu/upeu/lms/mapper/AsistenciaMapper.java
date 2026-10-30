package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.AsistenciaRequest;
import pe.edu.upeu.lms.dto.AsistenciaResponse;
import pe.edu.upeu.lms.entity.Asistencia;

@Mapper(componentModel = "spring", uses = SesionClaseMapper.class)
public interface AsistenciaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sesionClase", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Asistencia toEntity(AsistenciaRequest request);

    AsistenciaResponse toResponse(Asistencia asistencia);
}
