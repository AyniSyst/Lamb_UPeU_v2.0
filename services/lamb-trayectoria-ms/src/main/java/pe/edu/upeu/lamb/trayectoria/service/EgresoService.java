package pe.edu.upeu.lamb.trayectoria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lamb.trayectoria.dto.*;
import pe.edu.upeu.lamb.trayectoria.entity.*;
import pe.edu.upeu.lamb.trayectoria.exception.BusinessRuleException;
import pe.edu.upeu.lamb.trayectoria.exception.ResourceNotFoundException;
import pe.edu.upeu.lamb.trayectoria.mapper.EgresoMapper;
import pe.edu.upeu.lamb.trayectoria.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EgresoService {

    private final EgresoRepository egresoRepo;
    private final SolicitudConstanciaRepository constanciaRepo;
    private final SituacionCierreRepository situacionRepo;
    private final InformeTrayectoriaRepository informeRepo;
    private final CumplimientoRequisitoRepository cumplimientoRepo;
    private final TrayectoriaRepository trayectoriaRepository;
    private final EgresoMapper mapper;

    // Egresos
    @Transactional(readOnly = true)
    public List<EgresoResponse> listarEgresos(Long idPersona) {
        List<Egreso> data = idPersona != null ? egresoRepo.findByIdPersona(idPersona) : egresoRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EgresoResponse obtenerEgreso(Long id) {
        return mapper.toResponse(buscarEgreso(id));
    }

    @Transactional
    public EgresoResponse crearEgreso(EgresoRequest r) {
        Trayectoria trayectoria = validarTrayectoria(r.idTrayectoria(), r.idPersona());
        if (egresoRepo.existsByIdTrayectoria(r.idTrayectoria())) {
            throw new BusinessRuleException("La trayectoria ya tiene un egreso registrado");
        }
        if (cumplimientoRepo.existsByIdTrayectoriaAndCumplidoFalse(r.idTrayectoria())) {
            throw new BusinessRuleException("No se puede registrar el egreso: existen requisitos pendientes");
        }
        Egreso e = mapper.toEntity(r);
        e.setTodosRequisitosCumplidos(true);
        if (e.getFechaEgreso() == null) {
            e.setFechaEgreso(LocalDate.now());
        }
        if (e.getCreditosTotales() == null) {
            e.setCreditosTotales(trayectoria.getCreditosAprobados() == null ? 0 : trayectoria.getCreditosAprobados());
        }
        if (e.getPromedioFinal() == null) {
            e.setPromedioFinal(trayectoria.getPromedioPonderado());
        }
        trayectoria.setEstado(EstadoTrayectoria.EGRESADO);
        return mapper.toResponse(egresoRepo.save(e));
    }

    @Transactional
    public EgresoResponse actualizarEgreso(Long id, EgresoRequest r) {
        Egreso e = buscarEgreso(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        LocalDate fechaAnterior = e.getFechaEgreso();
        Integer creditosAnteriores = e.getCreditosTotales();
        mapper.update(e, r);
        if (e.getFechaEgreso() == null) {
            e.setFechaEgreso(fechaAnterior == null ? LocalDate.now() : fechaAnterior);
        }
        if (e.getCreditosTotales() == null) {
            e.setCreditosTotales(creditosAnteriores == null ? 0 : creditosAnteriores);
        }
        return mapper.toResponse(e);
    }

    @Transactional
    public void eliminarEgreso(Long id) {
        egresoRepo.delete(buscarEgreso(id));
    }

    // Solicitudes de constancia
    @Transactional(readOnly = true)
    public List<SolicitudConstanciaResponse> listarConstancias(Long idPersona) {
        List<SolicitudConstancia> data = idPersona != null
                ? constanciaRepo.findByIdPersonaOrderByFechaSolicitudDesc(idPersona)
                : constanciaRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SolicitudConstanciaResponse obtenerConstancia(Long id) {
        return mapper.toResponse(buscarConstancia(id));
    }

    @Transactional
    public SolicitudConstanciaResponse crearConstancia(SolicitudConstanciaRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        SolicitudConstancia s = mapper.toEntity(r);
        if (s.getEstado() == null || s.getEstado().isBlank()) {
            s.setEstado("PENDIENTE");
        }
        if (s.getFechaSolicitud() == null) {
            s.setFechaSolicitud(LocalDateTime.now());
        }
        return mapper.toResponse(constanciaRepo.save(s));
    }

    @Transactional
    public SolicitudConstanciaResponse actualizarConstancia(Long id, SolicitudConstanciaRequest r) {
        SolicitudConstancia s = buscarConstancia(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        LocalDateTime fechaAnterior = s.getFechaSolicitud();
        mapper.update(s, r);
        if (s.getFechaSolicitud() == null) {
            s.setFechaSolicitud(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
        }
        if (s.getEstado() == null || s.getEstado().isBlank()) {
            s.setEstado("PENDIENTE");
        }
        return mapper.toResponse(s);
    }

    @Transactional
    public void eliminarConstancia(Long id) {
        constanciaRepo.delete(buscarConstancia(id));
    }

    // Situaciones de cierre
    @Transactional(readOnly = true)
    public List<SituacionCierreResponse> listarSituaciones(Long idPersona) {
        List<SituacionCierre> data = idPersona != null ? situacionRepo.findByIdPersona(idPersona) : situacionRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SituacionCierreResponse obtenerSituacion(Long id) {
        return mapper.toResponse(buscarSituacion(id));
    }

    @Transactional
    public SituacionCierreResponse crearSituacion(SituacionCierreRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        if (situacionRepo.findByIdTrayectoriaAndPeriodo(r.idTrayectoria(), r.periodo()).isPresent()) {
            throw new BusinessRuleException("Ya existe una situacion de cierre para la trayectoria y periodo indicados");
        }
        SituacionCierre s = mapper.toEntity(r);
        if (s.getCreditosAprobados() == null) {
            s.setCreditosAprobados(0);
        }
        if (s.getFechaCierre() == null) {
            s.setFechaCierre(LocalDateTime.now());
        }
        return mapper.toResponse(situacionRepo.save(s));
    }

    @Transactional
    public SituacionCierreResponse actualizarSituacion(Long id, SituacionCierreRequest r) {
        SituacionCierre s = buscarSituacion(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        LocalDateTime fechaAnterior = s.getFechaCierre();
        mapper.update(s, r);
        if (s.getCreditosAprobados() == null) {
            s.setCreditosAprobados(0);
        }
        if (s.getFechaCierre() == null) {
            s.setFechaCierre(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
        }
        return mapper.toResponse(s);
    }

    @Transactional
    public void eliminarSituacion(Long id) {
        situacionRepo.delete(buscarSituacion(id));
    }

    // Informes de trayectoria
    @Transactional(readOnly = true)
    public List<InformeTrayectoriaResponse> listarInformes(Long idPersona) {
        List<InformeTrayectoria> data = idPersona != null
                ? informeRepo.findByIdPersonaOrderByFechaGeneracionDesc(idPersona)
                : informeRepo.findAll();
        return data.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public InformeTrayectoriaResponse obtenerInforme(Long id) {
        return mapper.toResponse(buscarInforme(id));
    }

    @Transactional
    public InformeTrayectoriaResponse crearInforme(InformeTrayectoriaRequest r) {
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        InformeTrayectoria i = mapper.toEntity(r);
        if (i.getFechaGeneracion() == null) {
            i.setFechaGeneracion(LocalDateTime.now());
        }
        return mapper.toResponse(informeRepo.save(i));
    }

    @Transactional
    public InformeTrayectoriaResponse actualizarInforme(Long id, InformeTrayectoriaRequest r) {
        InformeTrayectoria i = buscarInforme(id);
        validarTrayectoria(r.idTrayectoria(), r.idPersona());
        LocalDateTime fechaAnterior = i.getFechaGeneracion();
        mapper.update(i, r);
        if (i.getFechaGeneracion() == null) {
            i.setFechaGeneracion(fechaAnterior == null ? LocalDateTime.now() : fechaAnterior);
        }
        return mapper.toResponse(i);
    }

    @Transactional
    public void eliminarInforme(Long id) {
        informeRepo.delete(buscarInforme(id));
    }

    private Trayectoria validarTrayectoria(Long idTrayectoria, Long idPersona) {
        Trayectoria t = trayectoriaRepository.findById(idTrayectoria)
                .orElseThrow(() -> new ResourceNotFoundException("Trayectoria no encontrada: " + idTrayectoria));
        if (!t.getIdPersona().equals(idPersona)) {
            throw new BusinessRuleException("La trayectoria no pertenece a la persona indicada");
        }
        return t;
    }

    private Egreso buscarEgreso(Long id) {
        return egresoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Egreso no encontrado: " + id));
    }

    private SolicitudConstancia buscarConstancia(Long id) {
        return constanciaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud de constancia no encontrada: " + id));
    }

    private SituacionCierre buscarSituacion(Long id) {
        return situacionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Situacion de cierre no encontrada: " + id));
    }

    private InformeTrayectoria buscarInforme(Long id) {
        return informeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Informe de trayectoria no encontrado: " + id));
    }
}
