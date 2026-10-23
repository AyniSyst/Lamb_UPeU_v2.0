package pe.edu.upeu.lamb.curriculo.mapper;

import pe.edu.upeu.lamb.curriculo.dto.PlanCursoRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanCursoResponse;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioRequest;
import pe.edu.upeu.lamb.curriculo.dto.PlanEstudioResponse;
import pe.edu.upeu.lamb.curriculo.entity.EstadoPlan;
import pe.edu.upeu.lamb.curriculo.entity.PlanCurso;
import pe.edu.upeu.lamb.curriculo.entity.PlanEstudio;
import pe.edu.upeu.lamb.curriculo.event.PlanPublicadoEvento;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class PlanEstudioMapper {

    public PlanEstudio toEntity(PlanEstudioRequest request) {
        return PlanEstudio.builder()
                .idPrograma(request.getIdPrograma())
                .version(request.getVersion().trim())
                .nombre(request.getNombre().trim())
                .estado(EstadoPlan.BORRADOR)
                .fechaRegistro(Instant.now())
                .build();
    }

    public PlanCurso toEntity(PlanCursoRequest request, PlanEstudio plan) {
        return PlanCurso.builder()
                .plan(plan)
                .codigoCurso(request.getCodigoCurso().trim().toUpperCase())
                .nombreCurso(request.getNombreCurso().trim())
                .ciclo(request.getCiclo())
                .creditos(request.getCreditos())
                .tipo(request.getTipo())
                .build();
    }

    public PlanEstudioResponse toResponse(PlanEstudio plan) {
        return PlanEstudioResponse.builder()
                .id(plan.getId())
                .idPrograma(plan.getIdPrograma())
                .version(plan.getVersion())
                .nombre(plan.getNombre())
                .estado(plan.getEstado())
                .fechaRegistro(plan.getFechaRegistro())
                .fechaPublicacion(plan.getFechaPublicacion())
                .totalCreditos(plan.getCursos().stream().mapToInt(PlanCurso::getCreditos).sum())
                .cursos(plan.getCursos().stream().map(this::toResponse).toList())
                .build();
    }

    public PlanCursoResponse toResponse(PlanCurso curso) {
        return PlanCursoResponse.builder()
                .id(curso.getId())
                .codigoCurso(curso.getCodigoCurso())
                .nombreCurso(curso.getNombreCurso())
                .ciclo(curso.getCiclo())
                .creditos(curso.getCreditos())
                .tipo(curso.getTipo())
                .prerrequisitos(codigosPrerrequisitos(curso))
                .build();
    }

    public PlanPublicadoEvento toPlanPublicadoEvento(PlanEstudio plan, Long idPlanDerogado, String origen) {
        return PlanPublicadoEvento.builder()
                .tipoEvento("plan.publicado")
                .planId(plan.getId())
                .idPrograma(plan.getIdPrograma())
                .version(plan.getVersion())
                .idPlanDerogado(idPlanDerogado)
                .codigosCursos(plan.getCursos().stream().map(PlanCurso::getCodigoCurso).toList())
                .origen(origen)
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

    private List<String> codigosPrerrequisitos(PlanCurso curso) {
        return curso.getPrerrequisitos().stream()
                .map(p -> p.getRequisito().getCodigoCurso())
                .sorted()
                .toList();
    }
}
