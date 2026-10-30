package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.InsigniaRequest;
import pe.edu.upeu.lms.dto.InsigniaResponse;
import pe.edu.upeu.lms.entity.Insignia;

@Mapper(componentModel = "spring")
public interface InsigniaMapper {

    InsigniaResponse toResponse(Insignia insignia);

    @Mapping(target = "id", ignore = true)
    Insignia toEntity(InsigniaRequest request);
}
