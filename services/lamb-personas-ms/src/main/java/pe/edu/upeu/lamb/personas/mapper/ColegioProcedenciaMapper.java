package pe.edu.upeu.lamb.personas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.personas.dto.ColegioProcedenciaRequest;
import pe.edu.upeu.lamb.personas.dto.ColegioProcedenciaResponse;
import pe.edu.upeu.lamb.personas.entity.ColegioProcedencia;

@Component
public class ColegioProcedenciaMapper {

    public ColegioProcedencia toEntity(ColegioProcedenciaRequest request) {
        return ColegioProcedencia.builder()
                .idPersona(request.getIdPersona())
                .nombreColegio(request.getNombreColegio())
                .nivelInstruccion(request.getNivelInstruccion())
                .build();
    }

    public ColegioProcedenciaResponse toResponse(ColegioProcedencia entity) {
        return ColegioProcedenciaResponse.builder()
                .id(entity.getId())
                .idPersona(entity.getIdPersona())
                .nombreColegio(entity.getNombreColegio())
                .nivelInstruccion(entity.getNivelInstruccion())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}
