package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.MallaCurricularRequest;
import pe.edu.upeu.lms.dto.MallaCurricularResponse;
import pe.edu.upeu.lms.entity.MallaCurricular;

@Mapper(componentModel = "spring")
public interface MallaCurricularMapper {

    MallaCurricularResponse toResponse(MallaCurricular mallaCurricular);

    @Mapping(target = "id", ignore = true)
    MallaCurricular toEntity(MallaCurricularRequest request);
}
