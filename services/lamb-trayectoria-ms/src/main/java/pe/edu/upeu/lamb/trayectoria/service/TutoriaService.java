package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.ProgramaTutoria;
import pe.edu.upeu.lamb.trayectoria.entity.SesionTutoria;
import pe.edu.upeu.lamb.trayectoria.entity.Trayectoria;
import pe.edu.upeu.lamb.trayectoria.entity.Tutoria;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.TutoriaMapper;
import pe.edu.upeu.lamb.trayectoria.repository.ProgramaTutoriaRepository;
import pe.edu.upeu.lamb.trayectoria.repository.SesionTutoriaRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TrayectoriaRepository;
import pe.edu.upeu.lamb.trayectoria.repository.TutoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutoriaService {

    private final ProgramaTutoriaRepository programaRepo;
    private final TutoriaRepository tutoriaRepo;
    private final SesionTutoriaRepository sesionRepo;
    private final TrayectoriaRepository trayectoriaRepository;
    private final TutoriaMapper mapper;

    // Programas de tutoria
    @Transactional(readOnly = true)
    public List<ProgramaTutoriaResponse> listarProgramas() {
        return programaRepo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ProgramaTutoriaResponse obtenerPrograma(Long id) {
        return mapper.toResponse(buscarPrograma(id));
    }

    @Transactional
    public ProgramaTutoriaResponse crearPrograma(ProgramaTutoriaRequest r) {
        if (programaRepo.existsByCodigo(r.codigo())) {
            throw new BusinessRuleException("Ya existe un programa de tutoria con el codigo " + r.codigo());
        }
        return mapper.toResponse(programaRepo.save(mapper.toEntity(r)));
    }

    @Transactional
    public ProgramaTutoriaResponse actualizarPrograma(Long id, ProgramaTutoriaRequest r) {
        ProgramaTutoria p = buscarPrograma(id);
        if (programaRepo.existsByCodigoAndIdNot(r.codigo(), id)) {
            throw new BusinessRuleException("Ya existe un programa de tutoria con el codigo " + r.codigo());
        }
        mapper.update(p, r);
        return mapper.toResponse(p);
    }

    @Transactional
    public void eliminarPrograma(Long id) {
        programaRepo.delete(buscarPrograma(id));
    }

    // Tutorias (cabecera-detalle)
    @Transactional(readOnly = true)
    public List<TutoriaResponse> listarTutorias(Long idPersona) {
        List<Tutoria> data = idPersona != null ? tutoriaRepo.findByIdPersona(idPersona) : tutoriaRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TutoriaResponse obtenerTutoria(Long id) {
        return mapper.toResponse(buscarTutoria(id));
    }

    @Transactional
    public TutoriaResponse crearTutoria(TutoriaRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        validarPrograma(r.idProgramaTutoria());
        Tutoria t = mapper.toEntity(r);
        if (t.getEstado() == null || t.getEstado().isBlank()) {
            t.setEstado("ACTIVA");
        }
        agregarSesiones(t, r.sesiones());
        return mapper.toResponse(tutoriaRepo.save(t));
    }

    @Transactional
    public TutoriaResponse actualizarTutoria(Long id, TutoriaRequest r) {
        Tutoria t = buscarTutoria(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        validarPrograma(r.idProgramaTutoria());
        mapper.update(t, r);
        if (t.getEstado() == null || t.getEstado().isBlank()) {
            t.setEstado("ACTIVA");
        }
        t.getSesiones().clear();
        agregarSesiones(t, r.sesiones());
        return mapper.toResponse(t);
    }

    @Transactional
    public void eliminarTutoria(Long id) {
        tutoriaRepo.delete(buscarTutoria(id));
    }

    // Sesiones de tutoria
    @Transactional(readOnly = true)
    public List<SesionTutoriaResponse> listarSesiones(Long idTutoria) {
        return sesionRepo.findByTutoriaIdOrderByFechaSesionAsc(idTutoria).stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SesionTutoriaResponse obtenerSesion(Long id) {
        return mapper.toResponse(buscarSesion(id));
    }

    @Transactional
    public SesionTutoriaResponse crearSesion(Long idTutoria, SesionTutoriaRequest r) {
        Tutoria tutoria = buscarTutoria(idTutoria);
        SesionTutoria s = mapper.toEntity(r);
        tutoria.agregarSesion(s);
        return mapper.toResponse(sesionRepo.save(s));
    }

    @Transactional
    public SesionTutoriaResponse actualizarSesion(Long id, SesionTutoriaRequest r) {
        SesionTutoria s = buscarSesion(id);
        mapper.update(s, r);
        return mapper.toResponse(s);
    }

    @Transactional
    public void eliminarSesion(Long id) {
        sesionRepo.delete(buscarSesion(id));
    }

    private void agregarSesiones(Tutoria t, List<SesionTutoriaRequest> sesiones) {
        if (sesiones == null) {
            return;
        }
        for (SesionTutoriaRequest sr : sesiones) {
            t.agregarSesion(mapper.toEntity(sr));
        }
    }

    private void validarTrayectoria(Long idTrayectoria, Long idPersona) {
        Trayectoria t = trayectoriaRepository.findById(idTrayectoria)
                .orElseThrow(() -> new ResourceNotFoundException("Trayectoria no encontrada: " + idTrayectoria));
        if (!t.getIdPersona().equals(idPersona)) {
            throw new BusinessRuleException("La trayectoria no pertenece a la persona indicada");
        }
    }

    private void validarPrograma(Long idProgramaTutoria) {
        if (idProgramaTutoria == null) {
            return;
        }
        if (!programaRepo.existsById(idProgramaTutoria)) {
            throw new ResourceNotFoundException("Programa de tutoria no encontrado: " + idProgramaTutoria);
        }
    }

    private ProgramaTutoria buscarPrograma(Long id) {
        return programaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa de tutoria no encontrado: " + id));
    }

    private Tutoria buscarTutoria(Long id) {
        return tutoriaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutoria no encontrada: " + id));
    }

    private SesionTutoria buscarSesion(Long id) {
        return sesionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion de tutoria no encontrada: " + id));
    }
}
