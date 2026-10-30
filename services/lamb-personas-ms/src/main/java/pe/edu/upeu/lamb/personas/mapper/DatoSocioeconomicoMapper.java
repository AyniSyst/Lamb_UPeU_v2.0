package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.DatoSocioeconomicoRequest;
import pe.edu.upeu.lamb.personas.dto.DatoSocioeconomicoResponse;
import pe.edu.upeu.lamb.personas.entity.DatoSocioeconomico;

@Component
public class DatoSocioeconomicoMapper {

    public DatoSocioeconomico toEntity(DatoSocioeconomicoRequest request) {
        return DatoSocioeconomico.builder()
                .idPersona(request.getIdPersona())
                .nivelSocioeconomico(request.getNivelSocioeconomico())
                .ingresoFamiliar(request.getIngresoFamiliar())
                .build();
    }

    public DatoSocioeconomicoResponse toResponse(DatoSocioeconomico entity) {
        return DatoSocioeconomicoResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .nivelSocioeconomico(entity.getNivelSocioeconomico())
                .ingresoFamiliar(entity.getIngresoFamiliar())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
