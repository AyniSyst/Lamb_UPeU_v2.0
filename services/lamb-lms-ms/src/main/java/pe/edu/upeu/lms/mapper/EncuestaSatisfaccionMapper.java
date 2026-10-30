package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.EncuestaSatisfaccionRequest;
import pe.edu.upeu.lms.dto.EncuestaSatisfaccionResponse;
import pe.edu.upeu.lms.entity.EncuestaSatisfaccion;

@Mapper(componentModel = "spring", uses = CursoMapper.class)
public interface EncuestaSatisfaccionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    EncuestaSatisfaccion toEntity(EncuestaSatisfaccionRequest request);

    EncuestaSatisfaccionResponse toResponse(EncuestaSatisfaccion encuestaSatisfaccion);
}
