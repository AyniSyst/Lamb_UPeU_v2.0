package pe.edu.upeu.lamb.trayectoria.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.ProgramaTutoria;
import pe.edu.upeu.lamb.trayectoria.entity.SesionTutoria;
import pe.edu.upeu.lamb.trayectoria.entity.Tutoria;

import java.util.List;

@Component
public class TutoriaMapper {

    public ProgramaTutoria toEntity(ProgramaTutoriaRequest r) {
        return ProgramaTutoria.builder()
                .codigo(r.codigo())
                .nombre(r.nombre())
                .descripcion(r.descripcion())
                .activo(!Boolean.FALSE.equals(r.activo()))
                .build();
    }

    public void update(ProgramaTutoria p, ProgramaTutoriaRequest r) {
        p.setCodigo(r.codigo());
        p.setNombre(r.nombre());
        p.setDescripcion(r.descripcion());
        p.setActivo(!Boolean.FALSE.equals(r.activo()));
    }

    public ProgramaTutoriaResponse toResponse(ProgramaTutoria p) {
        return new ProgramaTutoriaResponse(p.getId(), p.getCodigo(), p.getNombre(), p.getDescripcion(), p.isActivo());
    }

    public Tutoria toEntity(TutoriaRequest r) {
        return Tutoria.builder()
                .idTrayectoria(r.idTrayectoria())
                .idPersona(r.idPersona())
                .idTutor(r.idTutor())
                .idProgramaTutoria(r.idProgramaTutoria())
                .programaTutoria(r.programaTutoria())
                .periodo(r.periodo())
                .estado(r.estado())
                .fechaInicio(r.fechaInicio())
                .fechaFin(r.fechaFin())
                .build();
    }

    public void update(Tutoria t, TutoriaRequest r) {
        t.setIdTrayectoria(r.idTrayectoria());
        t.setIdPersona(r.idPersona());
        t.setIdTutor(r.idTutor());
        t.setIdProgramaTutoria(r.idProgramaTutoria());
        t.setProgramaTutoria(r.programaTutoria());
        t.setPeriodo(r.periodo());
        t.setEstado(r.estado());
        t.setFechaInicio(r.fechaInicio());
        t.setFechaFin(r.fechaFin());
    }

    public TutoriaResponse toResponse(Tutoria t) {
        List<SesionTutoriaResponse> sesiones = t.getSesiones().stream().map(this::toResponse).toList();
        return new TutoriaResponse(t.getId(), t.getIdTrayectoria(), t.getIdPersona(), t.getIdTutor(),
                t.getIdProgramaTutoria(), t.getProgramaTutoria(), t.getPeriodo(), t.getEstado(),
                t.getFechaInicio(), t.getFechaFin(), sesiones);
    }

    public SesionTutoria toEntity(SesionTutoriaRequest r) {
        return SesionTutoria.builder()
                .fechaSesion(r.fechaSesion())
                .tema(r.tema())
                .observaciones(r.observaciones())
                .recomendaciones(r.recomendaciones())
                .compromisoEstudiante(r.compromisoEstudiante())
                .derivacionBienestar(r.derivacionBienestar())
                .build();
    }

    public void update(SesionTutoria s, SesionTutoriaRequest r) {
        s.setFechaSesion(r.fechaSesion());
        s.setTema(r.tema());
        s.setObservaciones(r.observaciones());
        s.setRecomendaciones(r.recomendaciones());
        s.setCompromisoEstudiante(r.compromisoEstudiante());
        s.setDerivacionBienestar(r.derivacionBienestar());
    }

    public SesionTutoriaResponse toResponse(SesionTutoria s) {
        Long idTutoria = s.getTutoria() == null ? null : s.getTutoria().getId();
        return new SesionTutoriaResponse(s.getId(), idTutoria, s.getFechaSesion(), s.getTema(),
                s.getObservaciones(), s.getRecomendaciones(), s.getCompromisoEstudiante(), s.getDerivacionBienestar());
    }
}
