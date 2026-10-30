package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.*;

import java.util.List;

@Component
public class RiesgoAcademicoMapper {

    public AlertaRiesgo toEntity(AlertaRiesgoRequest r) {
        return AlertaRiesgo.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .nivelRiesgo(r.nivelRiesgo())
                .tipoAlerta(r.tipoAlerta())
                .descripcion(r.descripcion())
                .valorDetectado(r.valorDetectado())
                .umbralRegla(r.umbralRegla())
                .numeroRepitencia(r.numeroRepitencia())
                .codigoCurso(r.codigoCurso())
                .estado(r.estado())
                .fechaDeteccion(r.fechaDeteccion())
                .fechaCierre(r.fechaCierre())
                .build();
    }

    public void update(AlertaRiesgo a, AlertaRiesgoRequest r) {
        a.setIdTrayectoria(r.idTrayectoria());
        a.setIdPersona(r.idPersona());
        a.setNivelRiesgo(r.nivelRiesgo());
        a.setTipoAlerta(r.tipoAlerta());
        a.setDescripcion(r.descripcion());
        a.setValorDetectado(r.valorDetectado());
        a.setUmbralRegla(r.umbralRegla());
        a.setNumeroRepitencia(r.numeroRepitencia());
        a.setCodigoCurso(r.codigoCurso());
        a.setEstado(r.estado());
        a.setFechaDeteccion(r.fechaDeteccion());
        a.setFechaCierre(r.fechaCierre());
    }

    public AlertaRiesgoResponse toResponse(AlertaRiesgo a) {
        return new AlertaRiesgoResponse(a.getId(), a.getIdTrayectoria(), a.getIdPersona(), a.getNivelRiesgo(),
                a.getTipoAlerta(), a.getDescripcion(), a.getValorDetectado(), a.getUmbralRegla(),
                a.getNumeroRepitencia(), a.getCodigoCurso(), a.getEstado(), a.getFechaDeteccion(), a.getFechaCierre());
    }

    public NivelRiesgo toEntity(NivelRiesgoRequest r) {
        return NivelRiesgo.builder()
                .codigo(r.codigo())
                .nombre(r.nombre())
                .descripcion(r.descripcion())
                .activo(!Boolean.FALSE.equals(r.activo()))
                .build();
    }

    public void update(NivelRiesgo n, NivelRiesgoRequest r) {
        n.setCodigo(r.codigo());
        n.setNombre(r.nombre());
        n.setDescripcion(r.descripcion());
        n.setActivo(!Boolean.FALSE.equals(r.activo()));
    }

    public NivelRiesgoResponse toResponse(NivelRiesgo n) {
        return new NivelRiesgoResponse(n.getId(), n.getCodigo(), n.getNombre(), n.getDescripcion(), n.isActivo());
    }

    public ReglaAlerta toEntity(ReglaAlertaRequest r) {
        return ReglaAlerta.builder()
                .nombre(r.nombre())
                .tipoAlerta(r.tipoAlerta())
                .umbral(r.umbral())
                .nivelRiesgo(r.nivelRiesgo())
                .activo(!Boolean.FALSE.equals(r.activo()))
                .descripcion(r.descripcion())
                .build();
    }

    public void update(ReglaAlerta a, ReglaAlertaRequest r) {
        a.setNombre(r.nombre());
        a.setTipoAlerta(r.tipoAlerta());
        a.setUmbral(r.umbral());
        a.setNivelRiesgo(r.nivelRiesgo());
        a.setActivo(!Boolean.FALSE.equals(r.activo()));
        a.setDescripcion(r.descripcion());
    }

    public ReglaAlertaResponse toResponse(ReglaAlerta a) {
        return new ReglaAlertaResponse(a.getId(), a.getNombre(), a.getTipoAlerta(), a.getUmbral(),
                a.getNivelRiesgo(), a.isActivo(), a.getDescripcion());
    }

    public CasoSeguimiento toEntity(CasoSeguimientoRequest r) {
        return CasoSeguimiento.builder()
                .idAlerta(r.idAlerta())
                .idPersona(r.idPersona())
                .idResponsable(r.idResponsable())
                .descripcionIntervencion(r.descripcionIntervencion())
                .estado(r.estado())
                .fechaApertura(r.fechaApertura())
                .fechaCierre(r.fechaCierre())
                .resultado(r.resultado())
                .build();
    }

    public void update(CasoSeguimiento c, CasoSeguimientoRequest r) {
        c.setIdAlerta(r.idAlerta());
        c.setIdPersona(r.idPersona());
        c.setIdResponsable(r.idResponsable());
        c.setDescripcionIntervencion(r.descripcionIntervencion());
        c.setEstado(r.estado());
        c.setFechaApertura(r.fechaApertura());
        c.setFechaCierre(r.fechaCierre());
        c.setResultado(r.resultado());
    }

    public CasoSeguimientoResponse toResponse(CasoSeguimiento c) {
        return new CasoSeguimientoResponse(c.getId(), c.getIdAlerta(), c.getIdPersona(), c.getIdResponsable(),
                c.getDescripcionIntervencion(), c.getEstado(), c.getFechaApertura(), c.getFechaCierre(), c.getResultado());
    }

    public ControlRepitencia toEntity(ControlRepitenciaRequest r) {
        return ControlRepitencia.builder()
                .idPersona(r.idPersona())
                .codigoCurso(r.codigoCurso())
                .numeroRepitencia(r.numeroRepitencia())
                .periodo(r.periodo())
                .estado(r.estado())
                .observacion(r.observacion())
                .fechaRegistro(r.fechaRegistro())
                .build();
    }

    public void update(ControlRepitencia c, ControlRepitenciaRequest r) {
        c.setIdPersona(r.idPersona());
        c.setCodigoCurso(r.codigoCurso());
        c.setNumeroRepitencia(r.numeroRepitencia());
        c.setPeriodo(r.periodo());
        c.setEstado(r.estado());
        c.setObservacion(r.observacion());
        c.setFechaRegistro(r.fechaRegistro());
    }

    public ControlRepitenciaResponse toResponse(ControlRepitencia c) {
        return new ControlRepitenciaResponse(c.getId(), c.getIdPersona(), c.getCodigoCurso(),
                c.getNumeroRepitencia(), c.getPeriodo(), c.getEstado(), c.getObservacion(), c.getFechaRegistro());
    }

    public PlanRefuerzo toEntity(PlanRefuerzoRequest r) {
        return PlanRefuerzo.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .periodo(r.periodo())
                .objetivo(r.objetivo())
                .estado(r.estado())
                .idResponsable(r.idResponsable())
                .fechaInicio(r.fechaInicio())
                .fechaFin(r.fechaFin())
                .observacion(r.observacion())
                .build();
    }

    public void update(PlanRefuerzo p, PlanRefuerzoRequest r) {
        p.setIdTrayectoria(r.idTrayectoria());
        p.setIdPersona(r.idPersona());
        p.setPeriodo(r.periodo());
        p.setObjetivo(r.objetivo());
        p.setEstado(r.estado());
        p.setIdResponsable(r.idResponsable());
        p.setFechaInicio(r.fechaInicio());
        p.setFechaFin(r.fechaFin());
        p.setObservacion(r.observacion());
    }

    public PlanRefuerzoResponse toResponse(PlanRefuerzo p) {
        List<CursoRefuerzoResponse> cursos = p.getCursos().stream().map(this::toResponse).toList();
        return new PlanRefuerzoResponse(p.getId(), p.getIdTrayectoria(), p.getIdPersona(), p.getPeriodo(),
                p.getObjetivo(), p.getEstado(), p.getIdResponsable(), p.getFechaInicio(), p.getFechaFin(),
                p.getObservacion(), cursos);
    }

    public CursoRefuerzo toEntity(CursoRefuerzoRequest r) {
        return CursoRefuerzo.builder()
                .codigoCurso(r.codigoCurso())
                .nombreCurso(r.nombreCurso())
                .numeroRepitencia(r.numeroRepitencia())
                .actividadAsignada(r.actividadAsignada())
                .completado(Boolean.TRUE.equals(r.completado()))
                .observacion(r.observacion())
                .build();
    }

    public CursoRefuerzoResponse toResponse(CursoRefuerzo c) {
        Long idPlan = c.getPlanRefuerzo() == null ? null : c.getPlanRefuerzo().getId();
        return new CursoRefuerzoResponse(c.getId(), idPlan, c.getCodigoCurso(), c.getNombreCurso(),
                c.getNumeroRepitencia(), c.getActividadAsignada(), c.isCompletado(), c.getObservacion());
    }
}
