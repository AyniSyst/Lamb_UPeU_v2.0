package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.RequisitoMatriculaRequest;
import pe.edu.upeu.lamb.curriculo.dto.RequisitoMatriculaResponse;
import pe.edu.upeu.lamb.curriculo.entity.RequisitoMatricula;
import org.springframework.stereotype.Component;

@Component
public class RequisitoMatriculaMapper {

    // Las relaciones (ids de otras tablas) las resuelve el servicio
    public RequisitoMatricula toEntity(RequisitoMatriculaRequest request) {
        return RequisitoMatricula.builder()
                .tipo(request.getTipo())
                .creditosMinimos(request.getCreditosMinimos())
                .cicloMinimo(request.getCicloMinimo())
                .descripcion(request.getDescripcion())
                .build();
    }

    public RequisitoMatriculaResponse toResponse(RequisitoMatricula requisitoMatricula) {
        return RequisitoMatriculaResponse.builder()
                .id(requisitoMatricula.getId())
                .idPlanCurso(requisitoMatricula.getPlanCurso().getId())
                .tipo(requisitoMatricula.getTipo())
                .idCursoRequisito(requisitoMatricula.getCursoRequisito() != null ? requisitoMatricula.getCursoRequisito().getId() : null)
                .creditosMinimos(requisitoMatricula.getCreditosMinimos())
                .cicloMinimo(requisitoMatricula.getCicloMinimo())
                .descripcion(requisitoMatricula.getDescripcion())
                .build();
    }
}
