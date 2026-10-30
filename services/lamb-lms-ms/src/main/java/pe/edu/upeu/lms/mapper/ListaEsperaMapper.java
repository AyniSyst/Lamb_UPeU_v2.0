package pe.edu.upeu.lms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.lms.dto.ListaEsperaRequest;
import pe.edu.upeu.lms.dto.ListaEsperaResponse;
import pe.edu.upeu.lms.entity.ListaEspera;

@Mapper(componentModel = "spring", uses = SeccionCursoMapper.class)
public interface ListaEsperaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seccionCurso", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    ListaEspera toEntity(ListaEsperaRequest request);

    ListaEsperaResponse toResponse(ListaEspera listaEspera);
}
