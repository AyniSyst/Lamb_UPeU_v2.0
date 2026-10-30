package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.*;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.RiesgoAcademicoMapper;
import pe.edu.upeu.lamb.trayectoria.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RiesgoAcademicoService {

    private final AlertaRiesgoRepository alertaRepo;
    private final NivelRiesgoRepository nivelRepo;
    private final ReglaAlertaRepository reglaRepo;
    private final CasoSeguimientoRepository casoRepo;
    private final ControlRepitenciaRepository repitenciaRepo;
    private final PlanRefuerzoRepository planRepo;
    private final TrayectoriaRepository trayectoriaRepository;
    private final RiesgoAcademicoMapper mapper;

    // Alertas
    @Transactional(readOnly = true)
    public List<AlertaRiesgoResponse> listarAlertas(Long idPersona) {
        List<AlertaRiesgo> data = idPersona != null
                ? alertaRepo.findByIdPersonaOrderByFechaDeteccionDesc(idPersona)
                : alertaRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public AlertaRiesgoResponse obtenerAlerta(Long id) {
        return mapper.toResponse(buscarAlerta(id));
    }

    @Transactional
    public AlertaRiesgoResponse crearAlerta(AlertaRiesgoRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        AlertaRiesgo a = mapper.toEntity(r);
        if (a.getEstado() == null || a.getEstado().isBlank()) {
            a.setEstado("ABIERTA");
        }
        if (a.getFechaDeteccion() == null) {
            a.setFechaDeteccion(LocalDateTime.now());
        }
        return mapper.toResponse(alertaRepo.save(a));
    }

    @Transactional
    public AlertaRiesgoResponse actualizarAlerta(Long id, AlertaRiesgoRequest r) {
        AlertaRiesgo a = buscarAlerta(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        LocalDateTime fechaAnterior = a.getFechaDeteccion();
        mapper.update(a, r);
        if (a.getFechaDeteccion() == null) {
            a.setFechaDeteccion(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
        }
        if (a.getEstado() == null || a.getEstado().isBlank()) {
            a.setEstado("ABIERTA");
        }
        return mapper.toResponse(a);
    }

    @Transactional
    public void eliminarAlerta(Long id) {
        alertaRepo.delete(buscarAlerta(id));
    }

    // Niveles de riesgo
    @Transactional(readOnly = true)
    public List<NivelRiesgoResponse> listarNiveles() {
        return nivelRepo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public NivelRiesgoResponse obtenerNivel(Long id) {
        return mapper.toResponse(buscarNivel(id));
    }

    @Transactional
    public NivelRiesgoResponse crearNivel(NivelRiesgoRequest r) {
        if (nivelRepo.existsByCodigo(r.codigo())) {
            throw new BusinessRuleException("Ya existe un nivel de riesgo con el codigo " + r.codigo());
        }
        return mapper.toResponse(nivelRepo.save(mapper.toEntity(r)));
    }

    @Transactional
    public NivelRiesgoResponse actualizarNivel(Long id, NivelRiesgoRequest r) {
        NivelRiesgo n = buscarNivel(id);
        if (nivelRepo.existsByCodigoAndIdNot(r.codigo(), id)) {
            throw new BusinessRuleException("Ya existe un nivel de riesgo con el codigo " + r.codigo());
        }
        mapper.update(n, r);
        return mapper.toResponse(n);
    }

    @Transactional
    public void eliminarNivel(Long id) {
        nivelRepo.delete(buscarNivel(id));
    }

    // Reglas de alerta
    @Transactional(readOnly = true)
    public List<ReglaAlertaResponse> listarReglas() {
        return reglaRepo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ReglaAlertaResponse obtenerRegla(Long id) {
        return mapper.toResponse(buscarRegla(id));
    }

    @Transactional
    public ReglaAlertaResponse crearRegla(ReglaAlertaRequest r) {
        return mapper.toResponse(reglaRepo.save(mapper.toEntity(r)));
    }

    @Transactional
    public ReglaAlertaResponse actualizarRegla(Long id, ReglaAlertaRequest r) {
        ReglaAlerta a = buscarRegla(id);
        mapper.update(a, r);
        return mapper.toResponse(a);
    }

    @Transactional
    public void eliminarRegla(Long id) {
        reglaRepo.delete(buscarRegla(id));
    }

    // Casos de seguimiento
    @Transactional(readOnly = true)
    public List<CasoSeguimientoResponse> listarCasos(Long idPersona) {
        List<CasoSeguimiento> data = idPersona != null ? casoRepo.findByIdPersona(idPersona) : casoRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CasoSeguimientoResponse obtenerCaso(Long id) {
        return mapper.toResponse(buscarCaso(id));
    }

    @Transactional
    public CasoSeguimientoResponse crearCaso(CasoSeguimientoRequest r) {
        buscarAlerta(r.idAlerta());
        CasoSeguimiento c = mapper.toEntity(r);
        if (c.getEstado() == null || c.getEstado().isBlank()) {
            c.setEstado("ABIERTO");
        }
        if (c.getFechaApertura() == null) {
            c.setFechaApertura(LocalDateTime.now());
        }
        return mapper.toResponse(casoRepo.save(c));
    }

    @Transactional
    public CasoSeguimientoResponse actualizarCaso(Long id, CasoSeguimientoRequest r) {
        CasoSeguimiento c = buscarCaso(id);
        buscarAlerta(r.idAlerta());
        LocalDateTime fechaAnterior = c.getFechaApertura();
        mapper.update(c, r);
        if (c.getFechaApertura() == null) {
            c.setFechaApertura(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
        }
        if (c.getEstado() == null || c.getEstado().isBlank()) {
            c.setEstado("ABIERTO");
        }
        return mapper.toResponse(c);
    }

    @Transactional
    public void eliminarCaso(Long id) {
        casoRepo.delete(buscarCaso(id));
    }

    // Control de repitencia
    @Transactional(readOnly = true)
    public List<ControlRepitenciaResponse> listarRepitencias(Long idPersona) {
        List<ControlRepitencia> data = idPersona != null
                ? repitenciaRepo.findByIdPersona(idPersona)
                : repitenciaRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ControlRepitenciaResponse obtenerRepitencia(Long id) {
        return mapper.toResponse(buscarRepitencia(id));
    }

    @Transactional
    public ControlRepitenciaResponse crearRepitencia(ControlRepitenciaRequest r) {
        trayectoriaRepository.findByIdPersona(r.idPersona())
                .orElseThrow(() -> new ResourceNotFoundException("La persona no tiene trayectoria academica"));
        ControlRepitencia c = mapper.toEntity(r);
        if (c.getEstado() == null || c.getEstado().isBlank()) {
            c.setEstado("REGISTRADA");
        }
        if (c.getFechaRegistro() == null) {
            c.setFechaRegistro(LocalDateTime.now());
        }
        return mapper.toResponse(repitenciaRepo.save(c));
    }

    @Transactional
    public ControlRepitenciaResponse actualizarRepitencia(Long id, ControlRepitenciaRequest r) {
        ControlRepitencia c = buscarRepitencia(id);
        trayectoriaRepository.findByIdPersona(r.idPersona())
                .orElseThrow(() -> new ResourceNotFoundException("La persona no tiene trayectoria academica"));
        LocalDateTime fechaAnterior = c.getFechaRegistro();
        mapper.update(c, r);
        if (c.getFechaRegistro() == null) {
            c.setFechaRegistro(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
        }
        if (c.getEstado() == null || c.getEstado().isBlank()) {
            c.setEstado("REGISTRADA");
        }
        return mapper.toResponse(c);
    }

    @Transactional
    public void eliminarRepitencia(Long id) {
        repitenciaRepo.delete(buscarRepitencia(id));
    }

    // Planes de refuerzo (cabecera-detalle)
    @Transactional(readOnly = true)
    public List<PlanRefuerzoResponse> listarPlanes(Long idPersona) {
        List<PlanRefuerzo> data = idPersona != null ? planRepo.findByIdPersona(idPersona) : planRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PlanRefuerzoResponse obtenerPlan(Long id) {
        return mapper.toResponse(buscarPlan(id));
    }

    @Transactional
    public PlanRefuerzoResponse crearPlan(PlanRefuerzoRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        PlanRefuerzo p = mapper.toEntity(r);
        if (p.getEstado() == null || p.getEstado().isBlank()) {
            p.setEstado("EN_CURSO");
        }
        if (p.getFechaInicio() == null) {
            p.setFechaInicio(LocalDate.now());
        }
        agregarCursos(p, r.cursos());
        return mapper.toResponse(planRepo.save(p));
    }

    @Transactional
    public PlanRefuerzoResponse actualizarPlan(Long id, PlanRefuerzoRequest r) {
        PlanRefuerzo p = buscarPlan(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        mapper.update(p, r);
        p.getCursos().clear();
        agregarCursos(p, r.cursos());
        return mapper.toResponse(p);
    }

    @Transactional
    public void eliminarPlan(Long id) {
        planRepo.delete(buscarPlan(id));
    }

    private void agregarCursos(PlanRefuerzo p, List<CursoRefuerzoRequest> cursos) {
        if (cursos == null || cursos.isEmpty()) {
            return;
        }
        Set<String> codigos = new HashSet<>();
        for (CursoRefuerzoRequest cr : cursos) {
            if (!codigos.add(cr.codigoCurso())) {
                throw new BusinessRuleException("El curso " + cr.codigoCurso() + " esta repetido en el plan de refuerzo");
            }
            p.agregarCurso(mapper.toEntity(cr));
        }
    }

    private void validarTrayectoria(Long idTrayectoria, Long idPersona) {
        Trayectoria t = trayectoriaRepository.findById(idTrayectoria)
                .orElseThrow(() -> new ResourceNotFoundException("Trayectoria no encontrada: " + idTrayectoria));
        if (!t.getIdPersona().equals(idPersona)) {
            throw new BusinessRuleException("La trayectoria no pertenece a la persona indicada");
        }
    }

    private AlertaRiesgo buscarAlerta(Long id) {
        return alertaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta de riesgo no encontrada: " + id));
    }

    private NivelRiesgo buscarNivel(Long id) {
        return nivelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nivel de riesgo no encontrado: " + id));
    }

    private ReglaAlerta buscarRegla(Long id) {
        return reglaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Regla de alerta no encontrada: " + id));
    }

    private CasoSeguimiento buscarCaso(Long id) {
        return casoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caso de seguimiento no encontrado: " + id));
    }

    private ControlRepitencia buscarRepitencia(Long id) {
        return repitenciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control de repitencia no encontrado: " + id));
    }

    private PlanRefuerzo buscarPlan(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de refuerzo no encontrado: " + id));
    }
}
